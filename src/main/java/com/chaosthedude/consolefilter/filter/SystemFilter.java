package com.chaosthedude.consolefilter.filter;

import java.io.PrintStream;

import com.chaosthedude.consolefilter.ConsoleFilter;
import com.chaosthedude.consolefilter.ConsoleFilterConfig;

public class SystemFilter extends PrintStream implements CustomFilter {

	private final ConsoleFilterConfig config;

	public SystemFilter(ConsoleFilter mod) {
		super(System.out, true);

		config = mod.getConfig();
	}

	@Override
	public void applyFilter(ConsoleFilter mod) {
		System.setOut(this);
	}

	@Override
	public void println(String s) {
		if (!shouldFilter(s)) {
			super.println(s);
		}
	}

	private boolean shouldFilter(String s) {
		return config.shouldFilter(s);
	}
}