package com.chaosthedude.consolefilter.filter;

import com.chaosthedude.consolefilter.ConsoleFilter;

import java.io.PrintStream;

public class SystemOutFilter extends PrintStream {

    private ConsoleFilter mod;

	public SystemOutFilter(ConsoleFilter mod) {
		super(System.out, true);
        System.setOut(this);
        this.mod = mod;
	}

    @Override
	public void println(String s) {
		if (!mod.shouldFilterMessage(s)) {
			super.println(s);
		}
	}

}