package com.kata.businessrules;

import static org.mockito.Mockito.mock;

public class CurrentMockedUsers implements CurrentUsers {

	private User customer;
	private User royaltyDepartment;	

	@Override
	public User getCustomer() {
		return customer;
	}

	@Override
	public User getRoyaltyDepartment() {
		return royaltyDepartment;
	}
	
	public CurrentMockedUsers()
	{
		customer = mock(User.class);
		royaltyDepartment = mock(User.class);
	}

}
