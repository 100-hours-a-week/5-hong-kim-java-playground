package com.example.demo.shop.domain.laptop;

import com.example.demo.shop.domain.EquipmentType;
import com.example.demo.shop.domain.Order;

public class Laptop extends Order {

	private OSType osType;

	public Laptop(
		String username,
		int quantity,
		String location,
		OSType osType
	) {
		super(username, quantity, osType.getPrice(), location, EquipmentType.LAPTOP);
		this.osType = osType;
	}

	public String getOsTypeToString() {
		return osType.toString();
	}

	public static class Builder {

		private String username;
		private int quantity;
		private String location;
		private OSType osType;

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

		public Builder osType(OSType osType) {
			this.osType = osType;
			return this;
		}

		public Laptop build() {
			return new Laptop(username, quantity, location, osType);
		}
	}
}
