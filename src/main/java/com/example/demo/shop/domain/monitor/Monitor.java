package com.example.demo.shop.domain.monitor;

import com.example.demo.shop.domain.EquipmentType;
import com.example.demo.shop.domain.Order;

public class Monitor extends Order {

	private MonitorType monitorType;

	public Monitor(
		String username,
		int quantity,
		String location,
		MonitorType monitorType
	) {
		super(username, quantity, monitorType.getPrice(), location, EquipmentType.MONITOR);
		this.monitorType = monitorType;
	}

	public String getMonitorTypeToString() {
		return monitorType.toString();
	}

	public static class Builder {

		private String username;
		private int quantity;
		private String location;
		private MonitorType monitorType;

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

		public Builder monitorType(MonitorType monitorType) {
			this.monitorType = monitorType;
			return this;
		}

		public Monitor build() {
			return new Monitor(username, quantity, location, monitorType);
		}
	}
}
