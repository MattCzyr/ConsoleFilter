package com.chaosthedude.consolefilter;

import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chaosthedude.consolefilter.filter.SystemFilter;
import com.chaosthedude.consolefilter.filter.JavaFilter;
import com.chaosthedude.consolefilter.filter.Log4jFilter;

import net.minecraftforge.fml.common.Mod;

@Mod(ConsoleFilter.MODID)
public class ConsoleFilter {

	public static final String MODID = "consolefilter";

	public static final Logger logger = LogManager.getLogger(MODID);

	public ConsoleFilter() {
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onCommonSetup);
		ModLoadingContext.get().registerConfig(
			ModConfig.Type.COMMON, ConsoleFilterConfig.SPEC, "consolefilter-common.toml"
		);
	}

	private void onCommonSetup(final FMLCommonSetupEvent event) {
		logger.info(ConsoleFilterConfig.filters.get().size() + " message(s) to be filtered.");

		JavaFilter.applyFilter();
		Log4jFilter.applyFilter();
		SystemFilter.applyFilter();
	}
}
