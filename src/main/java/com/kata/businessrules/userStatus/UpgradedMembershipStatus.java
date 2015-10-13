package com.kata.businessrules.userStatus;

import com.google.common.base.Preconditions;
import com.kata.businessrules.User;
import com.kata.businessrules.products.UpgradedMembership;

public class UpgradedMembershipStatus implements Status {

	@Override
	public boolean hasStatus(User user) {
		Preconditions.checkNotNull(user);
		return user.getPurchasedProducts().stream()
				.anyMatch(product -> product instanceof UpgradedMembership);
	}

}
