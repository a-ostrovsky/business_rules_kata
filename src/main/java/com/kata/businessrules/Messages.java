package com.kata.businessrules;

import java.util.Locale;
import java.util.ResourceBundle;

public class Messages {
	public static ThreadLocal<Locale> localeContext = ThreadLocal
			.withInitial(() -> new Locale("en"));

	public static String get(String message) {
		ResourceBundle messages = ResourceBundle.getBundle(
				"com.kata.businessrules.MessagesBundle", localeContext.get());
		return messages.getString(message);
	}

	public static void setLocale(Locale locale) {
		localeContext.set(locale);
	}
}
