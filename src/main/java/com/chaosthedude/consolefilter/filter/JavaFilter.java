package com.chaosthedude.consolefilter.filter;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.chaosthedude.consolefilter.ConsoleFilter;
import com.chaosthedude.consolefilter.config.ConfigHandler;

public class JavaFilter implements Filter {

	@Override
	public boolean isLoggable(LogRecord record) {
		for (String s : ConfigHandler.getMessagesToFilter()) {
			if (record.getMessage().contains(s)) {
				return false;
			}
		}
		return true;
	}

	public static void applyFilter() {
		Logger.getLogger("").setFilter(new JavaFilter());
	}

}
