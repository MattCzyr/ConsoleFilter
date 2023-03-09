package com.chaosthedude.consolefilter.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

import com.chaosthedude.consolefilter.ConsoleFilter;
import com.chaosthedude.consolefilter.ConsoleFilterConfig;

public class Log4jFilter implements CustomFilter, Filter {

	private final ConsoleFilterConfig config;

	public Log4jFilter(ConsoleFilter mod) {
		this.config = mod.getConfig();
	}

	@Override
	public void applyFilter(ConsoleFilter mod) {
		((Logger) LogManager.getRootLogger()).addFilter(this);
	}

	@Override
	public Filter.Result filter(LogEvent event) {
		Message message = event.getMessage();
		if (this.config.shouldFilter(message.toString()) || this.config.shouldFilter(message.getFormattedMessage())) {
			return Filter.Result.DENY;
		}
		return null;
	}

	@Override
	public State getState() {
		return null;
	}

	@Override
	public void initialize() {
	}

	@Override
	public void start() {
	}

	@Override
	public void stop() {
	}

	@Override
	public boolean isStarted() {
		return false;
	}

	@Override
	public boolean isStopped() {
		return false;
	}

	@Override
	public Result getOnMismatch() {
		return null;
	}

	@Override
	public Result getOnMatch() {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String msg, Object... params) {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0) {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1) {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2) {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3) {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3, Object p4) {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3, Object p4, Object p5) {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3, Object p4, Object p5, Object p6) {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3, Object p4, Object p5, Object p6, Object p7) {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
		return null;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
		return null;
	}
}