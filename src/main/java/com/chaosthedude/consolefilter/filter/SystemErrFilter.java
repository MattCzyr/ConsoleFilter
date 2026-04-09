package com.chaosthedude.consolefilter.filter;

import com.chaosthedude.consolefilter.ConsoleFilter;

import java.io.PrintStream;

public class SystemErrFilter extends PrintStream {

    private ConsoleFilter mod;

    public SystemErrFilter(ConsoleFilter mod) {
        super(System.err, true);
        System.setErr(this);
        this.mod = mod;
    }

    @Override
    public void println(String s) {
        if (!mod.shouldFilterMessage(s)) {
            super.println(s);
        }
    }

}