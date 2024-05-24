package com.example.demo.shop.domain.keyboard;

import com.example.demo.shop.domain.EquipmentType;
import com.example.demo.shop.domain.Order;

public class Keyboard extends Order {

	private LayoutType layoutType;

	public Keyboard(
		String username,
		int quantity,
		String location,
		LayoutType layoutType
	) {
		super(username, quantity, layoutType.getPrice(), location, EquipmentType.KEYBOARD);
		this.layoutType = layoutType;
	}

	public String getLayoutTypeToString() {
		return layoutType.toString();
	}

	public static class Builder {

		private String username;
		private int quantity;
		private String location;
		private LayoutType layoutType;

		public Builder username(String username) {
			this.username = username;
			return this;
		}

		public Builder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder location(String location) {
			this.location = location;
			return this;
		}

		public Builder layoutType(LayoutType layoutType) {
			this.layoutType = layoutType;
			return this;
		}

		public Keyboard build() {
			return new Keyboard(username, quantity, location, layoutType);
		}
	}
}
