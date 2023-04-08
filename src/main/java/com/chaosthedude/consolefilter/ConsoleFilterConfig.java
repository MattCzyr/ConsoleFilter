package com.chaosthedude.consolefilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConsoleFilterConfig {

	private ForgeConfigSpec.ConfigValue<List<? extends String>> basicFilters;
	private ForgeConfigSpec.ConfigValue<List<? extends String>> regexFilters;
	private ForgeConfigSpec spec;

	private List<FilterEntry> filterList = new ArrayList<>();

	public void init() {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

		builder.push("general");

		basicFilters = builder
				.comment("Any console messages containing any of these strings will be hidden.")
				.defineList("basicFilters", Collections.emptyList(), obj -> true);

		regexFilters = builder
				.comment("Any console messages that match any of these regular expressions will be hidden. Uses Java style regex. Backslashes must be escaped, for example use \\\\s instead of \\s to match whitespace.")
				.defineList("regexFilters", Collections.emptyList(), obj -> true);

		builder.pop();

		spec = builder.build();
	}

	public void load() {
		for (String entry : basicFilters.get()) {
			filterList.add(FilterEntry.wildcard(entry));
		}
		
		for (String entry : regexFilters.get()) {
			filterList.add(FilterEntry.regex(entry));
		}
	}

	public boolean shouldFilter(String message) {
		for (FilterEntry entry : filterList) {
			if (entry.shouldFilter(message)) {
				return true;
			}
		}

		return false;
	}

	public int filterCount() {
		return filterList.size();
	}

	public ForgeConfigSpec getSpec() {
		return spec;
	}
}