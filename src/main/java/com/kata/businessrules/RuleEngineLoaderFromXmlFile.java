package com.kata.businessrules;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import com.google.common.base.Preconditions;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Key;
import com.kata.businessrules.contact.DummyContactModule;
import com.kata.businessrules.payment.userDefinedRules.actions.ActionsModule;
import com.kata.businessrules.payment.userDefinedRules.xmlParsing.XmlParsingModule;

public class RuleEngineLoaderFromXmlFile {
	private RuleEngineLoader<Document> loader;

	public RuleEngine load(File file) throws RuleEngineCreationException,
			SAXException, ParserConfigurationException, IOException {
		Preconditions.checkNotNull(file);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
		Document doc = dBuilder.parse(file);
		return loader.load(doc);
	}

	public RuleEngineLoaderFromXmlFile() {
		Injector injector = Guice.createInjector(new ActionsModule(),
				new XmlParsingModule(), new MainModule(),
				new DummyContactModule());
		loader = injector.getInstance(new Key<RuleEngineLoader<Document>>() {
		});
	}
}
