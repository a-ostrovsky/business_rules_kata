package com.kata.businessrules.products;

import com.google.common.base.Preconditions;

public class Video implements Product {
	private String title;

	public Video(String title) {
		Preconditions.checkNotNull(title);
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((title == null) ? 0 : title.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (!(obj instanceof Video))
			return false;
		Video other = (Video) obj;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equals(other.title))
			return false;
		return true;
	}
}
