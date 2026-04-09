package com.chaosthedude.consolefilter.filter;

import java.util.logging.Filter;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import com.chaosthedude.consolefilter.ConsoleFilter;

public class JavaFilter implements Filter {

	private ConsoleFilter mod;

	public JavaFilter(ConsoleFilter mod) {
        this.mod = mod;
        Logger.getLogger("").setFilter(this);
	}

	@Override
	public boolean isLoggable(LogRecord record) {
		return !mod.shouldFilterMessage(record.getMessage()) && !mod.shouldFilterLogger(record.getLoggerName());
	}
}