package com.example.demo.shop;

import com.example.demo.member.MemberContainer;
import com.example.demo.shop.application.OrderService;
import com.example.demo.shop.domain.repository.OrderRepository;
import com.example.demo.shop.infrastructure.InMemoryOrderRepository;
import com.example.demo.shop.presentation.OrderController;
import com.example.demo.shop.presentation.ShopFacadeController;

// Initialization on demand holder idiom 방법으로 SingleTon 구현
public class ShopContainer {

	private ShopContainer() {  // 인스턴스화 방지
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
		private static final ShopFacadeController INSTANCE = new ShopFacadeController(orderController());
	}

	public static ShopFacadeController orderFacadeController() {
		return OrderFacadeControllerHolder.INSTANCE;
	}
}
