package com.chaosthedude.consolefilter.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

import com.chaosthedude.consolefilter.ConsoleFilter;
import com.chaosthedude.consolefilter.config.ConfigHandler;

public class Log4jFilter implements Filter {

	@Override
	public Filter.Result filter(LogEvent event) {
		for (String s : ConfigHandler.getMessagesToFilter()) {
			if (event.getMessage().toString().contains(s)) {
				return Filter.Result.DENY;
			}
		}
		return null;
	}

	@Override
	public Filter.Result filter(Logger logger, Level level, Marker marker, String s, Object... args) {
		return null;
	}

	@Override
	public Filter.Result filter(Logger logger, Level level, Marker marker, Object object, Throwable t) {
		return null;
	}

	@Override
	public Filter.Result filter(Logger logger, Level level, Marker marker, Message message, Throwable t) {
		return null;
	}

	@Override
	public Filter.Result getOnMatch() {
		return null;
	}

	@Override
	public Filter.Result getOnMismatch() {
		return null;
	}

	public static void applyFilter() {
		((Logger) LogManager.getRootLogger()).addFilter(new Log4jFilter());
	}

}
