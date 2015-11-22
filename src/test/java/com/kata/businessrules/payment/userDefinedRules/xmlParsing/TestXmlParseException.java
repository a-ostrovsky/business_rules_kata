package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import java.util.Optional;

import org.junit.Test;

import com.kata.businessrules.Messages;
import com.kata.businessrules.XmlElement;

public class TestXmlParseException {
	@Test
	public void constructor_nodeIsProvided_nodeIsInErrorMessage()
			throws Exception {		
		Exception exception = new XmlParseException(
				Optional.of(XmlElement.fromText("<invalidNode />")));
		assertThat(exception.getMessage(), containsString("invalidNode"));
	}

	@Test
	public void constructor_nodeIsNotProvided_explainatoryMessage()
			throws Exception {
		String expected = Messages.get("missing");
		Exception exception = new XmlParseException(Optional.empty());
		assertThat(exception.getMessage(), containsString(expected));
	}
}
