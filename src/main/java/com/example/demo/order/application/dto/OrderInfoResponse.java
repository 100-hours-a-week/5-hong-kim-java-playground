package com.example.demo.order.application.dto;

import com.example.demo.order.domain.Order;

public record OrderInfoResponse(
	String itemName,
	Long price,
	int quantity,
	Long totalPrice
) {

	public static OrderInfoResponse of(Order order) {
		return new OrderInfoResponse(
			order.getEquipmentType().getDescription(),
			order.getPrice(),
			order.getQuantity(),
			order.getTotalPrice()
		);
	}
}
