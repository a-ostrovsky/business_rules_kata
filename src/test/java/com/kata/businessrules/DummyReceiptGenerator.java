package com.kata.businessrules;

public class DummyReceiptGenerator implements ReceiptGenerator {

	@Override
	public Receipt generateReceipt() {
		return new Receipt() {

		};
	}

}
