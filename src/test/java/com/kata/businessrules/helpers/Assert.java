package com.kata.businessrules.helpers;

import static org.junit.Assert.*;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class Assert {
	public static <T> void assertDoesNotThrow(Action action) {
		try {
			action.invoke();
		} catch (Exception e) {
			fail(String.format(
					"Expected not to throw any exception, but thrown %1s (%2s)",
					e.toString(), 
					ExceptionUtils.getStackTrace(e)));
		}
	}
}
