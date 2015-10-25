package com.kata.businessrules.payment.userDefinedRules.xmlParsing;

import org.junit.Before;
import org.junit.Test;
import org.w3c.dom.Element;

import static org.mockito.Mockito.*;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

import com.kata.businessrules.InMemoryUserRepository;
import com.kata.businessrules.User;
import com.kata.businessrules.XmlElement;
import com.kata.businessrules.payment.userDefinedRules.actions.Selector;

public class TestReceiverWithIdParser {
	private static final String ROYALTY_DEPARTMENT_ID = "royaltyDepartment";
	private ReceiverWithIdParser parser;
	private InMemoryUserRepository userRepository;
	private User royaltyDepartment;

	@Before
	public void setup() {
		royaltyDepartment = mock(User.class);
		userRepository = new InMemoryUserRepository();
		userRepository.addUser(ROYALTY_DEPARTMENT_ID, royaltyDepartment);
		parser = new ReceiverWithIdParser(userRepository);
	}

	@Test
	public void parse_validElement_selectorForUserWithGivenId()
			throws Exception {
		Selector<User> selector = parser.parse(XmlElement
				.fromText(String.format("<does_not_matter receiverId=\"%s\" />",
						ROYALTY_DEPARTMENT_ID)));
		User customer = mock(User.class);
		assertThat(selector.select(customer), is(royaltyDepartment));
	}

	@Test
	public void canParse_receiverWithId_true() throws Exception {
		ParserAssert.canParse(parser,
				"<does_not_matter receiverId=\"royaltyDepartment\" />");
	}

	@Test
	public void canParse_receiverIdIsNotGiven_false() throws Exception {
		ParserAssert.cannotParse(parser,
				"<does_not_matter receiver=\"customer\" />");
	}

	@Test
	public void canParse_null_false() throws Exception {
		ParserAssert.cannotParse(parser, (Element) null);
	}
}
