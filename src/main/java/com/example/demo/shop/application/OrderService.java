package com.example.demo.shop.application;

import static com.example.demo.shop.exception.OrderExceptionType.*;

import java.util.List;
import java.util.stream.Collectors;

import com.example.demo.async.EventProducer;
import com.example.demo.member.application.MemberService;
import com.example.demo.member.domain.Member;
import com.example.demo.member.exception.MemberException;
import com.example.demo.shop.application.dto.OrderInfoResponse;
import com.example.demo.shop.domain.Order;
import com.example.demo.shop.domain.repository.OrderRepository;
import com.example.demo.shop.exception.OrderException;

public class OrderService {

	private final MemberService memberService;
	private final OrderRepository orderRepository;

	public OrderService(MemberService memberService, OrderRepository orderRepository) {
		this.memberService = memberService;
		this.orderRepository = orderRepository;
	}

	public void orderedItem(Order order) {
		Long totalPrice = order.getTotalPrice();

		try {
			memberService.processPayment(totalPrice);
		} catch (MemberException ex) {
			throw new OrderException(INSUFFICIENT_BALANCE);
		}

		EventProducer.publish(order);
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
