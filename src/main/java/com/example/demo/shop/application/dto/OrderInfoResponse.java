package com.example.demo.shop.application.dto;

import com.example.demo.shop.domain.Order;

public record OrderInfoResponse(
	String itemName,
	String orderDate,
	String location,
	Long price,
	int quantity,
	Long totalPrice
) {

	public static OrderInfoResponse of(Order order) {
		return new OrderInfoResponse(
			order.getEquipmentType().getDescription(),
			order.getCreatedTimeToString(),
			order.getLocation(),
			order.getPrice(),
			order.getQuantity(),
			order.getTotalPrice()
		);
	}
}
