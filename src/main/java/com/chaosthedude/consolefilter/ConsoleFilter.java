package com.chaosthedude.consolefilter;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;

import com.chaosthedude.consolefilter.filter.CustomFilter;
import com.chaosthedude.consolefilter.filter.JavaFilter;
import com.chaosthedude.consolefilter.filter.Log4jFilter;
import com.chaosthedude.consolefilter.filter.SystemFilter;
import com.mojang.logging.LogUtils;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

@Mod(ConsoleFilter.MODID)
public class ConsoleFilter {

	public static final String MODID = "consolefilter";

	private static final Logger LOGGER = LogUtils.getLogger();

	private final ConsoleFilterConfig config = new ConsoleFilterConfig();
	private final List<CustomFilter> filterRegistry = new ArrayList<>();

	public ConsoleFilter() {
		config.init();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, config.getSpec(), "consolefilter-common.toml");
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		config.load();

		LOGGER.info(config.filterCount() + " message(s) to be filtered.");

		filterRegistry.add(new SystemFilter(this));
		filterRegistry.add(new JavaFilter(this));
		filterRegistry.add(new Log4jFilter(this));

		for (CustomFilter filter : filterRegistry) {
			filter.applyFilter(this);
		}
	}

	public ConsoleFilterConfig getConfig() {
		return config;
	}
}
