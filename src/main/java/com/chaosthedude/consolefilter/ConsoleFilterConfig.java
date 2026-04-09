package com.chaosthedude.consolefilter;

import java.util.Collections;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConsoleFilterConfig {

    private static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final General GENERAL = new General(BUILDER);
    public static final ForgeConfigSpec SPEC = BUILDER.build();

    public static class General {
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> basicFilters;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> regexFilters;
        public final ForgeConfigSpec.ConfigValue<List<? extends String>> loggerFilters;

        public General(ForgeConfigSpec.Builder builder) {
            builder.push("general");

            basicFilters = builder
                    .comment("Any console messages containing any of these strings will be hidden.")
                    .defineList("basicFilters", Collections.emptyList(), obj -> true);

            regexFilters = builder
                    .comment("Any console messages matching any of these regular expressions will be hidden. Uses Java regex syntax. Backslashes must be escaped, i.e. use \\\\s instead of \\s to match whitespace.")
                    .defineList("regexFilters", Collections.emptyList(), obj -> true);

            loggerFilters = builder
                    .comment("Any console messages from loggers with a name containing any of these strings will be hidden.")
                    .defineList("loggerFilters", Collections.emptyList(), obj -> true);

            builder.pop();
        }
    }
}