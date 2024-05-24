package com.example.demo.shop.domain;

import java.time.LocalDateTime;

import com.example.demo.common.domain.BaseTimeEntity;

public class Order extends BaseTimeEntity {

	private String username;
	private int quantity;
	private Long price;
	private String location;
	private EquipmentType equipmentType;

	public Order(String username, int quantity, Long price, String location, EquipmentType equipmentType) {
		super(LocalDateTime.now());
		this.username = username;
		this.quantity = quantity;
		this.price = price;
		this.location = location;
		this.equipmentType = equipmentType;
	}

	public String getUsername() {
		return username;
	}

	public int getQuantity() {
		return quantity;
	}

	public Long getPrice() {
		return price;
	}

	public Long getTotalPrice() {
		return price * quantity;
	}

	public String getLocation() {
		return location;
	}

	public EquipmentType getEquipmentType() {
		return equipmentType;
	}

	public void updateOrderUsername(String username) {
		this.username = username;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
}
