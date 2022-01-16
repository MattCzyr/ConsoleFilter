package com.chaosthedude.consolefilter.filter;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.chaosthedude.consolefilter.ConsoleFilterConfig;

public class JavaFilter implements Filter {

	@Override
	public boolean isLoggable(LogRecord record) {
		for (String s : ConsoleFilterConfig.filters.get()) {
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
