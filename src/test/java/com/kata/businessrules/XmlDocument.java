package com.kata.businessrules;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class XmlDocument {
	public static Document fromText(String contents)
			throws ParserConfigurationException, SAXException, IOException {
		if (contents == null) {
			return null;
		}
		DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance()
				.newDocumentBuilder();
		Document document = documentBuilder
				.parse(new ByteArrayInputStream(contents.getBytes()));
		return document;
	}
}
