package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static com.kata.businessrules.matchers.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Document;

import com.kata.businessrules.XmlDocument;
import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.payment.userDefinedRules.UserDefinedRuleWithOneAction;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.filters.Filter;

public class TestXmlParser {

	private XmlParser parser;
	private ParserWithFixedResult<Action> actionParser;
	private ParserWithFixedResult<Filter> filterParser;

	@Before
	public void setup() throws Exception {
		filterParser = new ParserWithFixedResult<>(Filter.class);
		actionParser = new ParserWithFixedResult<>(Action.class);
		parser = new XmlParser(filterParser, actionParser);
	}

	@Test
	public void parse_xmlDocumentWithoutAnyFilterOrAction_emptyCollection()
			throws Exception {
		Document emptyRules = XmlDocument.fromText("<actions />");
		Collection<PaymentBehavior> behaviors = parser.parse(emptyRules);
		assertThat(behaviors.size(), is(0));
	}

	@Test
	public void parse_xmlDocumentWithOneFilterAndAction_thoseAreParsed()
			throws Exception {
		Document rules = XmlDocument
				.fromText("<actions><filter><action/></filter></actions>");
		Action parsedAction = actionParser.setCanParse("action");
		Filter parsedFilter = filterParser.setCanParse("filter");
		PaymentBehavior expectedResult = new UserDefinedRuleWithOneAction(
				parsedFilter, parsedAction);
		Collection<PaymentBehavior> behaviors = parser.parse(rules);
		assertThat(behaviors, isStructurallyEquivalentTo(expectedResult));
	}

	@Test
	public void parse_xmlDocumentWithTwoFiltersAndActions_thoseAreParsed()
			throws Exception {
		Document rules = XmlDocument
				.fromText("<actions><filter1><action1/></filter1>"
						+ "<filter2><action2/></filter2></actions>");
		actionParser.setCanParse("action1");
		filterParser.setCanParse("filter1");
		actionParser.setCanParse("action2");
		filterParser.setCanParse("filter2");
		Collection<PaymentBehavior> behaviors = parser.parse(rules);
		assertThat("Must parse two behaviors.", behaviors.size(), is(2));
	}

	// Comments
}
