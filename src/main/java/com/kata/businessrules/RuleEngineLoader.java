package com.kata.businessrules;

import java.util.Locale;
import java.util.ResourceBundle;

import com.google.common.base.Preconditions;
import com.google.inject.Inject;
import com.kata.businessrules.payment.PaymentBehaviorsParseException;
import com.kata.businessrules.payment.PaymentBehaviorsParser;

public class RuleEngineLoader<T> {

	private PaymentBehaviorsParser<T> parser;

	@Inject
	public RuleEngineLoader(PaymentBehaviorsParser<T> parser) {
		Preconditions.checkNotNull(parser);
		this.parser = parser;
	}

	public RuleEngine load(T representation)
			throws RuleEngineCreationException {
		try {
			return new RuleEngineForPaymentBehaviors(
					parser.parse(representation));
		} catch (PaymentBehaviorsParseException e) {
			throw new RuleEngineCreationException(getErrorMessage(), e);
		}
	}

	private String getErrorMessage() {
		ResourceBundle messages = ResourceBundle.getBundle(
				"com.kata.businessrules.MessagesBundle", new Locale("en"));
		return messages.getString("couldNotCreateRuleEngine");
	}

}
