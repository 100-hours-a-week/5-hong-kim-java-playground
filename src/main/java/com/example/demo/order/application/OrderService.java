package com.example.demo.order.application;

import static com.example.demo.order.exception.OrderExceptionType.*;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.member.domain.Member;
import com.example.demo.order.application.dto.OrderInfoResponse;
import com.example.demo.order.domain.Order;
import com.example.demo.order.domain.repository.OrderRepository;
import com.example.demo.order.exception.OrderException;

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
			throw new OrderException(INSUFFICIENT_BALANCE);
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
			.orElseThrow(() -> new OrderException(NOT_FOUND_ORDER_INFO));

		return orders.stream()
			.map(OrderInfoResponse::of)
			.collect(Collectors.toList());
	}
}
