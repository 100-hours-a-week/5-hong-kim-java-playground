package com.example.demo.order;

import com.example.demo.member.MemberContainer;
import com.example.demo.order.application.OrderService;
import com.example.demo.order.domain.repository.OrderRepository;
import com.example.demo.order.infrastructure.InMemoryOrderRepository;
import com.example.demo.order.presentation.OrderController;
import com.example.demo.order.presentation.OrderFacadeController;

// Initialization on demand holder idiom 방법으로 SingleTon 구현
public class OrderContainer {

	private OrderContainer() {  // 인스턴스화 방지
	}

	private static class OrderRepositoryHolder {
		private static final OrderRepository INSTANCE = new InMemoryOrderRepository();
	}

	public static OrderRepository orderRepository() {
		return OrderRepositoryHolder.INSTANCE;
	}

	private static class OrderServiceHolder {
		private static final OrderService INSTANCE = new OrderService(
			MemberContainer.memberService(),
			orderRepository()
		);
	}

	public static OrderService orderService() {
		return OrderServiceHolder.INSTANCE;
	}

	private static class OrderControllerHolder {
		private static final OrderController INSTANCE = new OrderController(orderService());
	}

	public static OrderController orderController() {
		return OrderControllerHolder.INSTANCE;
	}

	private static class OrderFacadeControllerHolder {
		private static final OrderFacadeController INSTANCE = new OrderFacadeController(orderController());
	}

	public static OrderFacadeController orderFacadeController() {
		return OrderFacadeControllerHolder.INSTANCE;
	}
}
