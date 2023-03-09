package com.chaosthedude.consolefilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import net.minecraftforge.common.ForgeConfigSpec;

public class ConsoleFilterConfig {

	private static final String REGEX_PATTERN = "regex:";

	private ForgeConfigSpec.ConfigValue<List<? extends String>> filter;
	private ForgeConfigSpec spec;

	private List<FilterEntry> filterList = new ArrayList<>();

	void initialize() {
		ForgeConfigSpec.Builder builder = new ForgeConfigSpec.Builder();

		builder.push("general");
		this.filter = builder
				.comment(" Any console messages containing one of these strings will be hidden.")
				.defineList("filters", Collections.emptyList(), obj -> true);
		builder.pop();

		this.spec = builder.build();
	}

	void load() {
		List<? extends String> filterList = this.filter.get();
		for (String entry : filterList) {
			if (entry.startsWith(REGEX_PATTERN)) {
				this.filterList.add(FilterEntry.regex(entry.substring(REGEX_PATTERN.length())));
			} else {
				this.filterList.add(FilterEntry.wildcard(entry));
			}
		}
	}

	public boolean shouldFilter(String message) {
		for (FilterEntry entry : this.filterList) {
			if (entry.shouldFilter(message)) {
				return true;
			}
		}

		return false;
	}

	public int filterCount() {
		return this.filterList.size();
	}

	public ForgeConfigSpec getSpec() {
		return this.spec;
	}
}