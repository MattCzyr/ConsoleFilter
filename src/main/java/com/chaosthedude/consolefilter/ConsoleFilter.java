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
		this.config.initialize();

		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::commonSetup);
		ModLoadingContext.get().registerConfig(
			ModConfig.Type.COMMON, this.config.getSpec(), "consolefilter-common.toml"
		);
	}

	private void commonSetup(final FMLCommonSetupEvent event) {
		this.config.load();

		LOGGER.info(config.filterCount() + " message(s) to be filtered.");

		this.filterRegistry.add(new SystemFilter(this));
		this.filterRegistry.add(new JavaFilter(this));
		this.filterRegistry.add(new Log4jFilter(this));

		for (CustomFilter filter : this.filterRegistry) {
			filter.applyFilter(this);
		}
	}

	public ConsoleFilterConfig getConfig() {
		return this.config;
	}
}
