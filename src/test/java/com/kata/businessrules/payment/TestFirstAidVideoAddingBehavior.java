package com.kata.businessrules.payment;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.DummyReceiptGenerator;
import com.kata.businessrules.Receipt;
import com.kata.businessrules.ReceiptGenerator;
import com.kata.businessrules.ReceiptWithVisibleInternals;
import com.kata.businessrules.User;
import com.kata.businessrules.products.Product;
import com.kata.businessrules.products.Video;

public class TestFirstAidVideoAddingBehavior {
	private PaymentBehavior behavior;
	private ReceiptGenerator receiptGenerator;
	private Video videoLearningToSki;
	private User customer;

	private void pay() {
		behavior.pay(customer, videoLearningToSki);
	}

	private Receipt expectedReceipt(Product product) {
		return new ReceiptWithVisibleInternals(customer, product);
	}

	private void assertUserReceivedReceipt(User user, Product product) {
		verify(user).issueReceipt(expectedReceipt(product));
	}

	@Before
	public void setup() {
		receiptGenerator = new DummyReceiptGenerator();
		customer = mock(User.class);
		behavior = new FirstAidVideoAddingBehavior(receiptGenerator);
		videoLearningToSki = new Video("Learning to Ski");
	}

	@Test
	public void pay_Video_receiptForFirstAidVideoIsAdded() {
		pay();
		Video firstAidVideo = new Video("First Aid");
		assertUserReceivedReceipt(customer, firstAidVideo);
	}

	@Test
	public void isApplicable_onlyTrueForVideosWithTitleFirstAid() {
		Product nonVideo = mock(Product.class);
		Product anotherVideo = new Video("Learning Java");
		assertThat("Must be applicable to video 'Learning to Ski'.",
				behavior.isApplicable(videoLearningToSki), is(true));
		assertThat("Must not be applicable to non video.",
				behavior.isApplicable(nonVideo), is(false));
		assertThat("Must not be applicable to another videos.",
				behavior.isApplicable(anotherVideo), is(false));
	}
}
