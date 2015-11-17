package com.kata.businessrules;

import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;

public class XmlElement {
	public static Element fromText(String contents)
			throws ParserConfigurationException, SAXException, IOException {
		Document document = XmlDocument.fromText(contents);
		if (document == null) {
			return null;
		}
		return document.getDocumentElement();
	}
}
