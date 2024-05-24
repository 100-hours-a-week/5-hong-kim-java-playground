package com.example.demo.shop.domain;

public enum EquipmentType {
	MONITOR("모니터"),
	LAPTOP("노트북"),
	KEYBOARD("키보드"),
	;

	private final String description;

	EquipmentType(String description) {
		this.description = description;
	}

	public String getDescription() {
		return description;
	}

	public static EquipmentType[] getEquipmentTypes() {
		return EquipmentType.values();
	}
}
