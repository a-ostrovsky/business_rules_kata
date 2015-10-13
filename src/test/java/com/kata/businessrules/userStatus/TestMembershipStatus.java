package com.kata.businessrules.userStatus;

import com.kata.businessrules.ProductFixture;
import com.kata.businessrules.products.Product;

public class TestMembershipStatus extends UserStatusBasedOnProductTestBase {	
	@Override
	protected Status createStatus() {
		return new MembershipStatus();
	}

	@Override
	protected Product createStatusProduct() {
		return ProductFixture.createSomeMembership();
	}

	@Override
	protected Product createNonStatusProduct() {
		return ProductFixture.createSomeBook();
	}
}
