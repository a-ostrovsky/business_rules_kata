package com.kata.businessrules;

import com.kata.businessrules.products.Product;

public class ReceiptWithVisibleInternals implements Receipt {
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((customer == null) ? 0 : customer.hashCode());
		result = prime * result + ((product == null) ? 0 : product.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ReceiptWithVisibleInternals other = (ReceiptWithVisibleInternals) obj;
		if (customer == null) {
			if (other.customer != null)
				return false;
		} else if (!customer.equals(other.customer))
			return false;
		if (product == null) {
			if (other.product != null)
				return false;
		} else if (!product.equals(other.product))
			return false;
		return true;
	}

	private User customer;
	private Product product;

	public User getCustomer() {
		return customer;
	}

	public Product getProduct() {
		return product;
	}

	public ReceiptWithVisibleInternals(User customer, Product product) {
		this.customer = customer;
		this.product = product;
	}
}
