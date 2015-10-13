package com.kata.businessrules.userStatus;

import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.products.Product;

public class TestUpgradedMembershipStatus extends UserStatusBasedOnProductTestBase {
	@Override
	protected Status createStatus() {
		return new UpgradedMembershipStatus();
	}

	@Override
	protected Product createStatusProduct() {
		return ProductFixture.createSomeUpgradedMembership();
	}

	@Override
	protected Product createNonStatusProduct() {
		return ProductFixture.createSomeBook();
	}
}
