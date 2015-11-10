package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import java.util.Collection;
import java.util.Set;

import org.w3c.dom.Element;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;

public class CombinationOfParsers<T> implements Parser<T> {

	private Collection<Parser<T>> parsers;

	@Inject
	public CombinationOfParsers(Set<Parser<T>> parsers) {
		Preconditions.checkNotNull(parsers);
		this.parsers = parsers;
	}

	@Override
	public T parse(Element element) {
		Preconditions.checkNotNull(element);
		Parser<T> matchingParser = parsers.stream()
				.filter(parser -> parser.canParse(element)).findFirst().get();
		return matchingParser.parse(element);
	}

	@Override
	public boolean canParse(Element element) {
		return parsers.stream().anyMatch(parser -> parser.canParse(element));
	}

}
