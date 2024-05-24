package com.example.demo.shop.domain.laptop;

import com.example.demo.shop.domain.EquipmentType;
import com.example.demo.shop.domain.Order;

public class Laptop extends Order {

	private OSType osType;

	public Laptop(
		int quantity,
		String location,
		OSType osType
	) {
		super(quantity, osType.getPrice(), location, EquipmentType.LAPTOP);
		this.osType = osType;
	}

	public OSType getOsType() {
		return osType;
	}

	public static class Builder {
		private int quantity;
		private String location;
		private OSType osType;

		public Builder quantity(int quantity) {
			this.quantity = quantity;
			return this;
		}

		public Builder location(String location) {
			this.location = location;
			return this;
		}

		public Builder osType(OSType osType) {
			this.osType = osType;
			return this;
		}

		public Laptop build() {
			return new Laptop(quantity, location, osType);
		}
	}
}
