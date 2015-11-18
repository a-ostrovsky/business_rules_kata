package com.kata.businessrules.matchers;

import java.util.Arrays;
import java.util.Collection;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.hamcrest.BaseMatcher;
import org.hamcrest.Description;

public class Matchers {

	private static <T> boolean arrayHasItem(T[] array, T item) {
		return Arrays.stream(array)
				.anyMatch(elt -> EqualsBuilder.reflectionEquals(elt, item));
	}

	@SafeVarargs
	public static <T> BaseMatcher<T> isStructurallyEquivalentTo(T... expected) {
		return new BaseMatcher<T>() {
			@Override
			public boolean matches(Object item) {
				Collection<?> collection = (Collection<?>) item;
				boolean lengthIsSame = collection.size() == expected.length;
				boolean actualCollectionHasAllITems = collection.stream()
						.allMatch(elt -> arrayHasItem(expected, elt));
				return lengthIsSame && actualCollectionHasAllITems;
			}

			@Override
			public void describeTo(Description description) {
				description.appendText("Two collections have different items.");
			}
		};
	}
}
