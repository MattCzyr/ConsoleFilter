package com.chaosthedude.consolefilter.filter;

import java.io.OutputStream;
import java.io.PrintStream;
import java.util.logging.LogRecord;

import com.chaosthedude.consolefilter.config.ConfigHandler;

public class SystemFilter extends PrintStream {
	
	public SystemFilter(OutputStream out) {
		super(out, true);
	}
	
	@Override
	public void println(String s) {
		if (!shouldFilter(s)) {
			super.println(s);
		}
	}
	
	private boolean shouldFilter(String s) {
		for (String filter : ConfigHandler.getMessagesToFilter()) {
			if (s.contains(filter)) {
				return true;
			}
		}
		return false;
	}
	
	public static void applyFilter() {
		System.setOut(new SystemFilter(System.out));
	}

}
