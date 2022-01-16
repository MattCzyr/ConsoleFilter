package com.chaosthedude.consolefilter;

import net.minecraftforge.common.ForgeConfigSpec;

import java.util.Collections;
import java.util.List;

public class ConsoleFilterConfig
{
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    public static final ForgeConfigSpec.ConfigValue<List<? extends String>> filters;

    static {
        BUILDER.push("general");

        filters = BUILDER
            .comment(" Any console messages containing one of these strings will be hidden.")
            .defineList("filters", Collections.emptyList(), obj -> true);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }
}
