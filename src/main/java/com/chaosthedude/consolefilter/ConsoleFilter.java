package com.chaosthedude.consolefilter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chaosthedude.consolefilter.filter.SystemErrFilter;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import org.apache.logging.log4j.LogManager;

import com.chaosthedude.consolefilter.filter.JavaFilter;
import com.chaosthedude.consolefilter.filter.Log4jFilter;
import com.chaosthedude.consolefilter.filter.SystemOutFilter;

import org.apache.logging.log4j.Logger;

@Mod(ConsoleFilter.MODID)
public class ConsoleFilter {

	public static final String MODID = "consolefilter";

	private static final Logger LOGGER = LogManager.getLogger(MODID);

    public List<Pattern> filterPatterns = new ArrayList<Pattern>();

	public ConsoleFilter(ModContainer modContainer) {
        modContainer.registerConfig(ModConfig.Type.COMMON, ConsoleFilterConfig.SPEC);
        modContainer.getEventBus().addListener(this::commonSetup);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
        int numFilters = ConsoleFilterConfig.GENERAL.basicFilters.get().size() + ConsoleFilterConfig.GENERAL.regexFilters.get().size() + ConsoleFilterConfig.GENERAL.loggerFilters.get().size();
        LOGGER.info("Loaded " + numFilters + " filter(s)");

        // Pre-compile regexes for performance
        for (String regex : ConsoleFilterConfig.GENERAL.regexFilters.get()) {
            if (!regex.isEmpty()) {
                filterPatterns.add(Pattern.compile(regex));
            }
        }

        new SystemOutFilter(this);
        new SystemErrFilter(this);
        new JavaFilter(this);
        new Log4jFilter(this);
	}

	public boolean shouldFilterMessage(String message) {
        if (message != null) {
            for (String str : ConsoleFilterConfig.GENERAL.basicFilters.get()) {
                if (!str.isEmpty() && message.contains(str)) {
                    return true;
                }
            }

            for (Pattern pattern : filterPatterns) {
                Matcher matcher = pattern.matcher(message);
                if (matcher.find()) {
                    return true;
                }
            }
        }
        return false;
    }

    public boolean shouldFilterLogger(String logger) {
        if (logger != null) {
            for (String str : ConsoleFilterConfig.GENERAL.loggerFilters.get()) {
                if (!str.isEmpty() && logger.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
