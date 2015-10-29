package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

public class TestCombinationOfParsers {

	private static final Element ELEMENT_TO_PARSE = mock(Element.class);
	private static final int PARSED_ELEMENT = 42;

	private Parser<Integer> firstParser;
	private Parser<Integer> secondParser;
	private CombinationOfParsers<Integer> combination;

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		firstParser = (Parser<Integer>) mock(Parser.class);
		secondParser = (Parser<Integer>) mock(Parser.class);
		combination = new CombinationOfParsers<>(
				Arrays.asList(firstParser, secondParser));
	}

	@Test
	public void canParse_noneOfParsersCanParse_false() {
		when(firstParser.canParse(ELEMENT_TO_PARSE)).thenReturn(false);
		when(secondParser.canParse(ELEMENT_TO_PARSE)).thenReturn(false);
		assertThat(combination.canParse(ELEMENT_TO_PARSE), is(false));
	}

	@Test
	public void canParse_someOfParsersCanParse_true() {
		when(firstParser.canParse(ELEMENT_TO_PARSE)).thenReturn(false);
		when(secondParser.canParse(ELEMENT_TO_PARSE)).thenReturn(true);
		assertThat(combination.canParse(ELEMENT_TO_PARSE), is(true));
	}

	@Test
	public void parse_someOfParsersCanParse_result() {
		when(firstParser.canParse(ELEMENT_TO_PARSE)).thenReturn(false);

		when(secondParser.canParse(ELEMENT_TO_PARSE)).thenReturn(true);
		when(secondParser.parse(ELEMENT_TO_PARSE)).thenReturn(PARSED_ELEMENT);
		
		assertThat(combination.parse(ELEMENT_TO_PARSE), is(PARSED_ELEMENT));
	}

}
