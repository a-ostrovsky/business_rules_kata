package com.kata.businessrules;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import com.kata.businessrules.payment.PaymentBehavior;
import com.kata.businessrules.payment.PaymentBehaviorsParseException;
import com.kata.businessrules.payment.PaymentBehaviorsParser;
import static com.kata.businessrules.helpers.Matchers.*;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Collection;

public class TestRuleEngineLoader {
	private Collection<PaymentBehavior> paymentBehaviors;
	private RuleEngineLoader<String> loader;
	private PaymentBehaviorsParser<String> parser;
	private String behaviorDefinition;

	@Rule
	public final ExpectedException exception = ExpectedException.none();

	@SuppressWarnings("unchecked")
	@Before
	public void setup() {
		paymentBehaviors = Arrays.asList();
		parser = (PaymentBehaviorsParser<String>) mock(
				PaymentBehaviorsParser.class);
		behaviorDefinition = "Payment behavior definition";
		loader = new RuleEngineLoader<>(parser);
	}

	@Test
	public void load_ruleEngineIsCreatedAccordingToSpecification()
			throws Exception {
		when(parser.parse(behaviorDefinition)).thenReturn(paymentBehaviors);
		RuleEngine ruleEngine = loader.load(behaviorDefinition);
		assertThat(ruleEngine, isStructurallyEqualTo(
				new RuleEngineForPaymentBehaviors(paymentBehaviors)));
	}

	@SuppressWarnings("unchecked")
	@Test
	public void load_ruleEngineCannotBeCreated_RuleEngineCreationException()
			throws Exception {
		when(parser.parse(behaviorDefinition))
				.thenThrow(PaymentBehaviorsParseException.class);
		exception.expect(RuleEngineCreationException.class);
		loader.load(behaviorDefinition);
	}
}
