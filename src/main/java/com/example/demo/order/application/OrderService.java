package com.example.demo.order.application;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.member.domain.Member;
import com.example.demo.order.application.dto.OrderInfoResponse;
import com.example.demo.order.domain.Order;
import com.example.demo.order.domain.repository.OrderRepository;

public class OrderService {

	private final OrderRepository orderRepository;

	public OrderService(OrderRepository orderRepository) {
		this.orderRepository = orderRepository;
	}

	public void orderedItem(Member member, Order order) {
		Long price = order.getTotalPrice();
		int quantity = order.getQuantity();
		long totalPrice = price * quantity;

		Long balance = member.getBalance();
		if (balance < totalPrice) {
			throw new RuntimeException("잔액 부족");
		}

		String username = member.getUsername();
		orderRepository.save(username, order);

		member.decreaseBalance(totalPrice);
	}

	public Long getOrderPrice(Order order) {
		return order.getTotalPrice();
	}

	public List<OrderInfoResponse> getOrderListByMember(Member member) {
		String username = member.getUsername();

		List<Order> orders = orderRepository.findByUsername(username)
			.orElseThrow(RuntimeException::new);

		return orders.stream()
			.map(OrderInfoResponse::of)
			.collect(Collectors.toList());
	}
}
