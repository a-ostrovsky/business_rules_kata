package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import java.io.StringWriter;
import java.util.Optional;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Element;

import com.kata.businessrules.Messages;
import com.kata.businessrules.payment.PaymentBehaviorsParseException;

public class XmlParseException extends PaymentBehaviorsParseException {

	private static final long serialVersionUID = -8597699346737938102L;

	public XmlParseException(Optional<Element> invalidElement) {
		super(String.format(Messages.get("couldNotParseXmlFile"),
				elementToString(invalidElement)));
	}

	private static String elementToString(Optional<Element> element) {
		try {
			if (!element.isPresent()) {
				return Messages.get("missing");
			}
			Transformer transformer = TransformerFactory.newInstance()
					.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION,
					"yes");
			StreamResult result = new StreamResult(new StringWriter());
			transformer.transform(new DOMSource(element.get()), result);
			return result.getWriter().toString();
		} catch (TransformerException e) {
			return e.getMessage();
		}
	}
}
