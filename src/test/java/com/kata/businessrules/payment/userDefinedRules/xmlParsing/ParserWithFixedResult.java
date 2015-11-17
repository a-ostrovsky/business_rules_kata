package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Element;
import static org.mockito.Mockito.mock;

public class ParserWithFixedResult<T> implements Parser<T> {

	private final Map<String, T> fixedResults = new HashMap<>();
	private Class<? extends T> resultClass;
	
	public ParserWithFixedResult(Class<? extends T> resultClass){
		this.resultClass = resultClass;		
	}

	public T setCanParse(String expectedElementName) {
		T fixedResult = mock(resultClass);
		fixedResults.put(expectedElementName, fixedResult);
		return fixedResult;
	}

	@Override
	public T parse(Element element) {
		return fixedResults.get(element.getTagName());
	}

	@Override
	public boolean canParse(Element element) {
		return fixedResults.containsKey(element.getTagName());
	}

}
