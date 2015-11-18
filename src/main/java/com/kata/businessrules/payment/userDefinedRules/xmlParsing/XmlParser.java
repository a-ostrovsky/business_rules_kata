package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.google.common.base.Preconditions;
import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.payment.PaymentBehaviorsParser;
import com.kata.businessrules.payment.userDefinedRules.UserDefinedRuleWithOneAction;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.filters.Filter;

public class XmlParser implements PaymentBehaviorsParser<Document> {

	private Parser<Filter> filterParser;
	private Parser<Action> actionParser;

	public XmlParser(Parser<Filter> filterParser, Parser<Action> actionParser) {
		Preconditions.checkNotNull(filterParser);
		Preconditions.checkNotNull(actionParser);
		this.filterParser = filterParser;
		this.actionParser = actionParser;
	}

	@Override
	public Collection<PaymentBehavior> parse(Document behaviorDefinition) {
		Preconditions.checkNotNull(behaviorDefinition);
		Element document = behaviorDefinition.getDocumentElement();
		NodeList children = document.getChildNodes();
		if (children.getLength() == 0) {
			return new ArrayList<>();
		}
		List<PaymentBehavior> result = new LinkedList<PaymentBehavior>();
		for (int i = 0; i < children.getLength(); i++) {
			if(!(children.item(i) instanceof Element))
			{
				continue;
			}
			Element filterAndActionElement = (Element) children.item(i);
			Filter filter = filterParser.parse(filterAndActionElement);
			Element actionElement = (Element) filterAndActionElement
					.getFirstChild();
			Action action = actionParser.parse(actionElement);
			result.add(new UserDefinedRuleWithOneAction(filter, action));
		}
		return result;
	}
}
