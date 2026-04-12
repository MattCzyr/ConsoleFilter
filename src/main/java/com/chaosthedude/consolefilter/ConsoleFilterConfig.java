package com.chaosthedude.consolefilter;

import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ConsoleFilterConfig {

    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final General GENERAL = new General(BUILDER);
    public static final ModConfigSpec SPEC = BUILDER.build();

    public static class General {
        public final ModConfigSpec.ConfigValue<List<? extends String>> basicFilters;
        public final ModConfigSpec.ConfigValue<List<? extends String>> regexFilters;
        public final ModConfigSpec.ConfigValue<List<? extends String>> loggerFilters;

        public General(ModConfigSpec.Builder builder) {
            builder.push("general");

            basicFilters = builder
                    .comment("Any console messages containing any of these strings will be hidden.")
                    .define("basicFilters", new ArrayList<String>());

            regexFilters = builder
                    .comment("Any console messages containing any of these regular expressions will be hidden. Uses Java regex syntax. Backslashes must be doubled, i.e. use \\\\s instead of \\s to match whitespace.")
                    .define("regexFilters", new ArrayList<String>());

            loggerFilters = builder
                    .comment("Any console messages from loggers with a name containing any of these strings will be hidden.")
                    .define("loggerFilters", new ArrayList<String>());

            builder.pop();
        }
    }
}