package com.chaosthedude.consolefilter;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.chaosthedude.consolefilter.config.ConfigHandler;
import com.chaosthedude.consolefilter.filter.JavaFilter;
import com.chaosthedude.consolefilter.filter.Log4jFilter;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = ConsoleFilter.MODID, name = ConsoleFilter.NAME, version = ConsoleFilter.VERSION, acceptedMinecraftVersions = "[1.11]", acceptableRemoteVersions = "*")

public class ConsoleFilter {

	public static final String MODID = "consolefilter";
	public static final String NAME = "Console Filter";
	public static final String VERSION = "1.0.0";

	public static final Logger logger = LogManager.getLogger(MODID);

	@EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		ConfigHandler.loadConfig(event.getSuggestedConfigurationFile());
		logger.info(ConfigHandler.getMessagesToFilter().size() + " messages to be filtered.");

		JavaFilter.applyFilter();
		Log4jFilter.applyFilter();
	}

}
