package com.kata.businessrules.matchers;

import java.util.Collection;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class Matchers {
	public static <T> BaseMatcher<T> hasStructurallyEquivalentItem(T expected) {
		return new BaseMatcher<T>() {
			@Override
			public boolean matches(Object item) {
				@SuppressWarnings("unchecked")
				Collection<T> collection = (Collection<T>) item;
				return collection.stream().anyMatch(
						elt -> EqualsBuilder.reflectionEquals(elt, expected));
			}

			@Override
			public void describeTo(Description description) {
				description.appendText(
						"Expected item was not found in the provided collection");
			}

		};

	}
}
