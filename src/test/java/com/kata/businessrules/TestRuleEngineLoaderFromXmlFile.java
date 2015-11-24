package com.kata.businessrules;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.net.URL;

import org.junit.Before;
import org.junit.Test;

public class TestRuleEngineLoaderFromXmlFile {

	private static File saveXml(String xml) throws IOException {
		File file = File.createTempFile("BusinessRules", "Test");
		file.deleteOnExit();
		Writer writer = new FileWriter(file);
		writer.write(xml);
		writer.flush();
		writer.close();
		return file;
	}

	private RuleEngineLoaderFromXmlFile loader;

	@Before
	public void setup() {
		loader = new RuleEngineLoaderFromXmlFile();
	}

	@Test
	public void load_xmlFileIsCorrect_ruleEngineIsLoaded() throws Exception {
		//@formatter:off
		String xml = 
				  "<?xml version=\"1.0\" encoding=\"UTF-8\"?>"
				+ "<actions xmlns=\"http://www.businessrules.com\""
				+ "  xmlns:xs=\"http://www.w3.org/2001/XMLSchema-instance\""
				+ "  xs:schemaLocation=\"http://www.businessrules.com rules.xsd\">"
				+ "  <!-- =================RULE 1=================== -->"
				+ "  <whenPaidFor productTitle=\"Learning to Ski\">"
				+ "    <receiptFor productId=\"First_Aid_Id\" receiver=\"customer\" />"
				+ "  </whenPaidFor>"
				+ "  <!-- =================RULE 2=================== -->"
				+ "  <whenPaidFor productType=\"Membership\">"
				+ "    <sendMessage receiver=\"customer\">"
				+ "      <message>Welcome...</message>" + "</sendMessage>"
				+ "    </whenPaidFor>"
				+ "  <!-- =================RULE 3=================== -->"
				+ "  <whenPaidFor productType=\"PhysicalProduct\">"
				+ "    <receiptFor product=\"bought\" receiver=\"customer\" />"
				+ "  </whenPaidFor>"
				+ "  <!-- =================RULE 4=================== -->"
				+ "  <whenPaidFor productType=\"Book\">"
				+ "    <receiptFor product=\"bought\" receiverId=\"royaltyDepartment\" />"
				+ "  </whenPaidFor>" 
				+ "</actions>";
		//@formatter:on
		final int numberOfRulesInXmlFile = 4;
		File file = saveXml(xml);
		RuleEngine ruleEngine = loader.load(file);
		assertThat("Unexpected type of the loaded rule engine", ruleEngine,
				instanceOf(RuleEngineForPaymentBehaviors.class));
		assertThat("Wrong number of parsed rules",
				((RuleEngineForPaymentBehaviors) ruleEngine)
						.getCountOfPaymentBehaviors(),
				is(numberOfRulesInXmlFile));
	}
}
