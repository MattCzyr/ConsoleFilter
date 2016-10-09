package com.chaosthedude.consolefilter.config;

import java.io.File;
import java.util.List;

import com.chaosthedude.consolefilter.ConsoleFilter;
import com.google.common.collect.Lists;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigHandler {

	public static Configuration config;

	public static String[] messagesToFilter = {};

	public static void loadConfig(File configFile) {
		config = new Configuration(configFile);

		config.load();
		init();

		MinecraftForge.EVENT_BUS.register(new ChangeListener());
	}

	public static void init() {
		messagesToFilter = loadStringArray("Any console messages containing one of these strings will be hidden.", "messagesToFilter", messagesToFilter);

		if (config.hasChanged()) {
			config.save();
		}
	}

	public static String[] loadStringArray(String comment, String name, String[] def) {
		Property prop = config.get(Configuration.CATEGORY_GENERAL, name, def);
		prop.setComment(comment);
		return prop.getStringList();
	}

	public static List<String> getMessagesToFilter() {
		return Lists.newArrayList(messagesToFilter);
	}

	public static class ChangeListener {
		@SubscribeEvent
		public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent eventArgs) {
			if (eventArgs.getModID().equals(ConsoleFilter.MODID)) {
				init();
			}
		}
	}

}
