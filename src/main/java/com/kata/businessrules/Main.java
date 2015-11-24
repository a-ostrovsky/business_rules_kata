package com.kata.businessrules;

import java.io.File;

public class Main {
	public static void main(String[] args) throws Exception {
		new RuleEngineLoaderFromXmlFile().load(new File("C:\\rules.xml"));
	}
}
