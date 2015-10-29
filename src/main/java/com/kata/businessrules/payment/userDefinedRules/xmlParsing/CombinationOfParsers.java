package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import java.util.Collection;

import org.w3c.dom.Element;

import com.google.common.base.Preconditions;

public class CombinationOfParsers<T> implements Parser<T> {

	private Collection<Parser<T>> parsers;

	public CombinationOfParsers(Collection<Parser<T>> parsers) {
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
