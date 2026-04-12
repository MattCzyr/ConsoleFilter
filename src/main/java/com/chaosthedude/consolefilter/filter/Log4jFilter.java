package com.chaosthedude.consolefilter.filter;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Marker;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.LogEvent;
import org.apache.logging.log4j.core.Logger;
import org.apache.logging.log4j.message.Message;

import com.chaosthedude.consolefilter.ConsoleFilter;

public class Log4jFilter implements Filter {

	private ConsoleFilter mod;

	public Log4jFilter(ConsoleFilter mod) {
		this.mod = mod;
        ((Logger) LogManager.getRootLogger()).addFilter(this);
	}

    // All other filter() methods return neutral, so they will pass through to this method, where the filters are applied
	@Override
	public Filter.Result filter(LogEvent event) {
		if (mod.shouldFilterMessage(event.getMessage().getFormattedMessage()) || mod.shouldFilterLogger(event.getLoggerName())) {
			return Filter.Result.DENY;
		}
		return Filter.Result.NEUTRAL;
	}

	@Override
	public State getState() {
		return State.INITIALIZED;
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
		return Result.NEUTRAL;
	}

	@Override
	public Result getOnMatch() {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object... params) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3, Object p4) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3, Object p4, Object p5) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3, Object p4, Object p5, Object p6) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3, Object p4, Object p5, Object p6, Object p7) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3, Object p4, Object p5, Object p6, Object p7, Object p8) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, String message, Object p0, Object p1, Object p2,
			Object p3, Object p4, Object p5, Object p6, Object p7, Object p8, Object p9) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, Object msg, Throwable t) {
		return Result.NEUTRAL;
	}

	@Override
	public Result filter(Logger logger, Level level, Marker marker, Message msg, Throwable t) {
		return Result.NEUTRAL;
	}

}
