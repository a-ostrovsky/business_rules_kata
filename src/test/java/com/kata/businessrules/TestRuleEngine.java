package com.kata.businessrules;

import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class TestRuleEngine {
	private RuleEngine engine;
	private PackingSlipGenerator packingSlipGenerator;	

	@Before
	public void setup(){
		packingSlipGenerator = mock(PackingSlipGenerator.class);
		engine = new RuleEngine(packingSlipGenerator);
	}
	
	@Test
	public void payForPhysicalProduct_packaingSlipIsGenerated(){
		engine.payForPhysicalProduct();
		verify(packingSlipGenerator).generatePackingSlip();
	}
}
