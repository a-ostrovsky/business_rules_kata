package com.kata.businessrules.contact;

import com.kata.businessrules.User;
import com.kata.businessrules.products.Membership;

public class MembershipActivationMessage implements Message {
	
	private User customer;
	private Membership membership;	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((customer == null) ? 0 : customer.hashCode());
		result = prime * result
				+ ((membership == null) ? 0 : membership.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof MembershipActivationMessage))
			return false;
		MembershipActivationMessage other = (MembershipActivationMessage) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (membership == null) {
			if (other.membership != null)
				return false;
		} else if (!membership.equals(other.membership))
			return false;
		return true;
	}



	public MembershipActivationMessage(User customer, Membership membership) {
		this.customer = customer;
		this.membership = membership;
	}
	
}
