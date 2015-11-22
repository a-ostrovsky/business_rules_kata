package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import java.util.Arrays;
import java.util.Collection;

import org.w3c.dom.Document;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.multibindings.Multibinder;
import com.kata.businessrules.User;
import com.kata.businessrules.contact.Message;
import com.kata.businessrules.payment.PaymentBehaviorsParser;
import com.kata.businessrules.payment.userDefinedRules.actions.Action;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;
import com.kata.businessrules.payment.userDefinedRules.filters.Filter;
import com.kata.businessrules.products.Product;

public class XmlParsingModule extends AbstractModule {

	@Override
	protected void configure() {
		configureMessageParsers();
		configureProductSelectorParsers();
		configureActionParsers();
		configureFilterParsers();
		configureUserSelectorParsers();
		configurePaymentBehaviorsParser();
	}

	private void configurePaymentBehaviorsParser() {
		bind(new TypeLiteral<PaymentBehaviorsParser<Document>>() {
		}).to(XmlParser.class);		
	}

	private void configureUserSelectorParsers() {
		configureCobinationOfParsers(Arrays.asList(
				ReceiverIsCustomerParser.class, ReceiverWithIdParser.class),

				new TypeLiteral<Parser<Selector<User>>>() {
				}, new TypeLiteral<CombinationOfParsers<Selector<User>>>() {
				});
	}

	private void configureFilterParsers() {
		configureCobinationOfParsers(
				Arrays.asList(ProductTypeFilterParser.class,
						TitleFilterIgnoringCaseParser.class),

				new TypeLiteral<Parser<Filter>>() {
				}, new TypeLiteral<CombinationOfParsers<Filter>>() {
				});
	}

	private void configureProductSelectorParsers() {
		configureCobinationOfParsers(Arrays.asList(ProductWithIdParser.class,
				BoughtProductParser.class),

				new TypeLiteral<Parser<Selector<Product>>>() {
				}, new TypeLiteral<CombinationOfParsers<Selector<Product>>>() {
				});
	}

	private void configureActionParsers() {
		configureCobinationOfParsers(Arrays.asList(
				IssueReceiptActionParser.class, SendMessageActionParser.class),

				new TypeLiteral<Parser<Action>>() {
				}, new TypeLiteral<CombinationOfParsers<Action>>() {
				});
	}

	private void configureMessageParsers() {
		configureCobinationOfParsers(Arrays.asList(MessageParser.class),

				new TypeLiteral<Parser<Message>>() {
				}, new TypeLiteral<CombinationOfParsers<Message>>() {
				});
	}

	private <TParser> void configureCobinationOfParsers(
			Collection<Class<? extends Parser<TParser>>> registeredClasses,
			TypeLiteral<Parser<TParser>> parserType,
			TypeLiteral<CombinationOfParsers<TParser>> combinationOfParsersType) {

		Multibinder<Parser<TParser>> binder = Multibinder.newSetBinder(binder(),
				parserType);
		for (Class<? extends Parser<TParser>> registeredClass : registeredClasses) {
			binder.addBinding().to(registeredClass);
		}
		bind(parserType).to(combinationOfParsersType);
	}
}
