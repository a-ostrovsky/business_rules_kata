package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
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
		Collection<Element> xmlElements = getXmlElements(behaviorDefinition);
		List<PaymentBehavior> result = new LinkedList<>();
		for (Element filterAndActionElement : xmlElements) {
			Filter filter = parseFilter(filterAndActionElement);
			Action action = parseAction(filterAndActionElement);
			result.add(new UserDefinedRuleWithOneAction(filter, action));
		}
		return result;
	}

	private Collection<Element> getXmlElements(Document behaviorDefinition) {
		Element document = behaviorDefinition.getDocumentElement();
		NodeList children = document.getChildNodes();
		List<Element> result = new LinkedList<>();
		for (int i = 0; i < children.getLength(); i++) {
			Node item = children.item(i);
			if (item instanceof Element) {
				result.add((Element) item);
			}
		}
		return result;
	}

	private Filter parseFilter(Element filterAndActionElement) {
		Filter filter = filterParser.parse(filterAndActionElement);
		return filter;
	}

	private Action parseAction(Element filterAndActionElement) {
		Element actionElement = (Element) filterAndActionElement
				.getFirstChild();
		Action action = actionParser.parse(actionElement);
		return action;
	}
}
