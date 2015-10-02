package com.kata.businessrules;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

public class RuleEngine {

	final Logger logger = LoggerFactory.getLogger(RuleEngine.class);

	private final PackingSlipGenerator packingSlipGenerator;

	public RuleEngine(PackingSlipGenerator packingSlipGenerator) {
		Preconditions.checkNotNull(packingSlipGenerator);
		this.packingSlipGenerator = packingSlipGenerator;
	}

	public void payForPhysicalProduct() {
		logger.info("Customer paid for physical product.");
		packingSlipGenerator.generatePackingSlip();
	}

	public void payForBook() {
		logger.info("Customer paid for book.");
		packingSlipGenerator.generatePackingSlip();
		packingSlipGenerator.generatePackingSlip();
	}
}
