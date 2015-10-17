package com.kata.businessrules.payment.userDefinedRules.filters;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import com.kata.businessrules.products.Product;
import com.kata.businessrules.products.Video;

public class TestTitleFilterIgnoringCase {

	private Product productThatIsNotFilteredOut;
	private Product productThatIsNotFilteredOutWithDifferentCasingInTitle;
	private Product productThatIsFilteredOut;
	private Filter filter;

	@Before
	public void setup() {
		productThatIsNotFilteredOut = new Video("title_1");
		productThatIsNotFilteredOutWithDifferentCasingInTitle = new Video(
				"TITLE_1");
		productThatIsFilteredOut = new Video("title_2");
		filter = new TitleFilterIgnoringCase("title_1");
	}

	@Test
	public void isApplicable_ProductWithGivenTitle_true() {
		assertThat(filter.isApplicable(productThatIsNotFilteredOut), is(true));
	}

	@Test
	public void isApplicable_ProductWithGivenTitleInDifferentCase_true() {
		assertThat(
				filter.isApplicable(
						productThatIsNotFilteredOutWithDifferentCasingInTitle),
				is(true));
	}

	@Test
	public void isApplicable_NotProductWithGivenTitle_false() {
		assertThat(filter.isApplicable(productThatIsFilteredOut), is(false));
	}

	@Test
	public void isApplicable_null_false() {
		assertThat(filter.isApplicable(null), is(false));
	}
}
