package com.chaosthedude.consolefilter;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.chaosthedude.consolefilter.filter.SystemErrFilter;
import org.apache.logging.log4j.LogManager;

import com.chaosthedude.consolefilter.filter.JavaFilter;
import com.chaosthedude.consolefilter.filter.Log4jFilter;
import com.chaosthedude.consolefilter.filter.SystemOutFilter;

import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.Logger;

public class ConsoleFilter implements ModInitializer {

	public static final String MODID = "consolefilter";

	private static final Logger LOGGER = LogManager.getLogger(MODID);

    public List<Pattern> filterPatterns = new ArrayList<Pattern>();

	@Override
	public void onInitialize() {
		ConsoleFilterConfig.load();

        int numFilters = ConsoleFilterConfig.basicFilters.size() + ConsoleFilterConfig.regexFilters.size() + ConsoleFilterConfig.loggerFilters.size();
        LOGGER.info("Loaded " + numFilters + " filter(s)");

        // Pre-compile regexes for performance
        for (String regex : ConsoleFilterConfig.regexFilters) {
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
            for (String str : ConsoleFilterConfig.basicFilters) {
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
            for (String str : ConsoleFilterConfig.loggerFilters) {
                if (!str.isEmpty() && logger.contains(str)) {
                    return true;
                }
            }
        }
        return false;
    }
}
