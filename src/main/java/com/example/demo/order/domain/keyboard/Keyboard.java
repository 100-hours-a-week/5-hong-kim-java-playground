package com.example.demo.order.domain.keyboard;

import com.example.demo.order.domain.EquipmentType;
import com.example.demo.order.domain.Order;

public class Keyboard extends Order {

	private LayoutType layoutType;

	public Keyboard(
		int quantity,
		String location,
		LayoutType layoutType
	) {
		super(quantity, layoutType.getPrice(), location, EquipmentType.KEYBOARD);
		this.layoutType = layoutType;
	}

	public LayoutType getKeyboardLayoutType() {
		return layoutType;
	}

	public static class Builder {
		private int quantity;
		private String location;
		private LayoutType layoutType;

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
			return new Keyboard(quantity, location, layoutType);
		}
	}
}
