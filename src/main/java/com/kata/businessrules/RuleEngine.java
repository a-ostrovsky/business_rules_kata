package com.kata.businessrules;

import com.google.common.base.Preconditions;

public class RuleEngine {

	private final PackingSlipGenerator packingSlipGenerator;

	public RuleEngine(PackingSlipGenerator packingSlipGenerator) {
		Preconditions.checkNotNull(packingSlipGenerator);
		this.packingSlipGenerator = packingSlipGenerator;
	}

	public void payForPhysicalProduct() {
		// TODO Auto-generated method stub
		packingSlipGenerator.generatePackingSlip();
	}

}
