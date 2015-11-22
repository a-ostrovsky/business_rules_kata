package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.payment.PaymentBehaviorsParseException;
import com.kata.businessrules.payment.PaymentBehaviorsParser;
import com.kata.businessrules.payment.userDefinedRules.UserDefinedRuleWithOneAction;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.filters.Filter;

public class XmlParser implements PaymentBehaviorsParser<Document> {

	private Parser<Filter> filterParser;
	private Parser<Action> actionParser;

	@Inject
	public XmlParser(Parser<Filter> filterParser, Parser<Action> actionParser) {
		Preconditions.checkNotNull(filterParser);
		Preconditions.checkNotNull(actionParser);
		this.filterParser = filterParser;
		this.actionParser = actionParser;
	}

	@Override
	public Collection<PaymentBehavior> parse(Document behaviorDefinition)
			throws PaymentBehaviorsParseException {
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
		Element element = behaviorDefinition.getDocumentElement();
		return getXmlElements(element);
	}

	private Collection<Element> getXmlElements(Element element) {
		NodeList children = element.getChildNodes();
		List<Element> result = new LinkedList<>();
		for (int i = 0; i < children.getLength(); i++) {
			Node item = children.item(i);
			if (item instanceof Element) {
				result.add((Element) item);
			}
		}
		return result;
	}

	private Filter parseFilter(Element filterAndActionElement)
			throws PaymentBehaviorsParseException {
		verifyCanParseFilter(filterAndActionElement);
		Filter filter = filterParser.parse(filterAndActionElement);
		return filter;
	}

	private void verifyCanParseFilter(Element filterAndActionElement)
			throws PaymentBehaviorsParseException {
		if (!filterParser.canParse(filterAndActionElement)) {
			throw new PaymentBehaviorsParseException(getErrorMessage());
		}
	}

	private Action parseAction(Element filterAndActionElement)
			throws PaymentBehaviorsParseException {
		Optional<Element> actionElement = getXmlElements(filterAndActionElement)
				.stream().findFirst();
		verifyCanParseAction(actionElement);
		Action action = actionParser.parse(actionElement.get());
		return action;
	}

	private void verifyCanParseAction(Optional<Element> actionElement)
			throws PaymentBehaviorsParseException {
		if (!actionElement.isPresent()
				|| !actionParser.canParse(actionElement.get())) {
			throw new PaymentBehaviorsParseException(getErrorMessage());
		}
	}

	private String getErrorMessage() {
		ResourceBundle messages = ResourceBundle.getBundle(
				"com.kata.businessrules.MessagesBundle", new Locale("en"));
		return messages.getString("couldNotParseXmlFile");
	}
}
