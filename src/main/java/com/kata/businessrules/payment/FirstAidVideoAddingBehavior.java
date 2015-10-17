package com.kata.businessrules.payment;

import com.google.common.base.Preconditions;
import com.kata.businessrules.ProductRepository;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;
import com.kata.businessrules.products.Video;

public class FirstAidVideoAddingBehavior implements PaymentBehavior {

	public static final String FIRST_AID_VIDEO_ID = "First_Aid_Id";

	private ReceiptGenerator receiptGenerator;
	private ProductRepository productRepository;

	public FirstAidVideoAddingBehavior(ReceiptGenerator receiptGenerator,
			ProductRepository productRepository) {
		Preconditions.checkNotNull(receiptGenerator);
		Preconditions.checkNotNull(productRepository);
		this.productRepository = productRepository;
		this.receiptGenerator = receiptGenerator;
	}

	@Override
	public void pay(User customer, Product product) {
		Preconditions.checkNotNull(customer);
		Preconditions.checkNotNull(product);
		Video firstAid = productRepository.getById(FIRST_AID_VIDEO_ID);
		Receipt receipt = receiptGenerator.generateReceipt(customer, firstAid);
		customer.issueReceipt(receipt);
	}

	@Override
	public boolean isApplicable(Product product) {
		return product instanceof Video && ((Video) product).getTitle().trim()
				.equalsIgnoreCase("Learning to Ski");
	}
}
