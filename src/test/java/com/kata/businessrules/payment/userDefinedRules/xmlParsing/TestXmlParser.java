package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import static com.kata.businessrules.helpers.Matchers.*;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Collection;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.w3c.dom.Document;

import com.kata.businessrules.XmlDocument;
import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.payment.PaymentBehaviorsParseException;
import com.kata.businessrules.payment.userDefinedRules.UserDefinedRuleWithOneAction;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.filters.Filter;

public class TestXmlParser {

	private XmlParser parser;
	private ParserWithFixedResult<Action> actionParser;
	private ParserWithFixedResult<Filter> filterParser;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	private PaymentBehavior setCanParseFilterWithAction(String filter,
			String action) {
		Action parsedAction = actionParser.setCanParse(action);
		Filter parsedFilter = filterParser.setCanParse(filter);
		return new UserDefinedRuleWithOneAction(parsedFilter, parsedAction);
	}

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
		PaymentBehavior expectedResult = setCanParseFilterWithAction("filter",
				"action");
		Collection<PaymentBehavior> behaviors = parser.parse(rules);
		assertThat(behaviors, isStructurallyEquivalentTo(expectedResult));
	}

	@Test
	public void parse_xmlDocumentWithTwoFiltersAndActions_thoseAreParsed()
			throws Exception {
		Document rules = XmlDocument
				.fromText("<actions><filter1><action1/></filter1>"
						+ "<filter2><action2/></filter2></actions>");
		setCanParseFilterWithAction("filter1", "action1");
		setCanParseFilterWithAction("filter2", "action2");
		Collection<PaymentBehavior> behaviors = parser.parse(rules);
		assertThat("Must parse two behaviors.", behaviors.size(), is(2));
	}

	@Test
	public void parse_xmlDocumentWithComments_commentsAreIgnored()
			throws Exception {
		Document rules = XmlDocument
				.fromText("<actions><!--COMMENT--></actions>");
		Collection<PaymentBehavior> behaviors = parser.parse(rules);
		assertThat("Must ignore comments.", behaviors.size(), is(0));
	}

	@Test
	public void parse_cannotParseFilter_PaymentBehaviorParseException()
			throws Exception {
		Document rules = XmlDocument.fromText(
				"<actions><cannotParseThis><action/></cannotParseThis></actions>");
		setCanParseFilterWithAction("NOT_cannotParseThis", "action");
		exception.expect(PaymentBehaviorsParseException.class);
		parser.parse(rules);
	}

	@Test
	public void parse_cannotParseAction_PaymentBehaviorParseException()
			throws Exception {
		Document rules = XmlDocument.fromText(
				"<actions><filter><cannotParseThis/></filter></actions>");
		setCanParseFilterWithAction("filter", "NOT_cannotParseThis");
		exception.expect(PaymentBehaviorsParseException.class);
		parser.parse(rules);
	}
	
	@Test
	public void parse_someFilterHasNoAction_PaymentBehaviorParseException()
			throws Exception {
		Document rules = XmlDocument.fromText(
				"<actions><filter/></actions>");
		setCanParseFilterWithAction("filter", "does_not_matter");
		exception.expect(PaymentBehaviorsParseException.class);
		parser.parse(rules);
	}
}
