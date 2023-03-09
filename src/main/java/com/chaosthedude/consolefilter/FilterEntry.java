package com.chaosthedude.consolefilter;

import java.util.regex.Pattern;

public interface FilterEntry {

	public static FilterEntry regex(String regex) {
		Pattern pattern = Pattern.compile(regex);
		return message -> {
			return pattern.matcher(message).matches();
		};
	}

	public static FilterEntry wildcard(String wildcard) {
		return message -> message.contains(wildcard);
	}

	boolean shouldFilter(String message);
}
