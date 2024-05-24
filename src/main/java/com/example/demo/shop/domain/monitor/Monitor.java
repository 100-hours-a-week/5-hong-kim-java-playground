package com.example.demo.shop.domain.monitor;

import com.example.demo.shop.domain.EquipmentType;
import com.example.demo.shop.domain.Order;

public class Monitor extends Order {

	private MonitorType monitorType;

	public Monitor(
		int quantity,
		String location,
		MonitorType monitorType
	) {
		super(quantity, monitorType.getPrice(), location, EquipmentType.MONITOR);
		this.monitorType = monitorType;
	}

	public MonitorType getMonitorType() {
		return monitorType;
	}

	public static class Builder {
		private int quantity;
		private String location;
		private MonitorType monitorType;

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
			return new Monitor(quantity, location, monitorType);
		}
	}
}
