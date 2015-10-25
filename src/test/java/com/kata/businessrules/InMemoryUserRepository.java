package com.kata.businessrules;

import java.util.HashMap;
import java.util.Map;

public class InMemoryUserRepository implements UserRepository {
	
	private final Map<String, User> users = new HashMap<>();

	@Override
	public User getById(String id) {
		return users.get(id);
	}
	
	public void addUser(String id, User user) {
		users.put(id, user);
	}

}
