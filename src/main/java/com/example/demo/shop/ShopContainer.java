package com.example.demo.shop;

import com.example.demo.member.MemberContainer;
import com.example.demo.shop.application.OrderService;
import com.example.demo.shop.application.ReviewService;
import com.example.demo.shop.domain.repository.OrderRepository;
import com.example.demo.shop.domain.repository.ReviewRepository;
import com.example.demo.shop.infrastructure.InMemoryOrderRepository;
import com.example.demo.shop.infrastructure.InMemoryReviewRepository;
import com.example.demo.shop.presentation.OrderController;
import com.example.demo.shop.presentation.ReviewController;
import com.example.demo.shop.presentation.ShopFacadeController;

// Initialization on demand holder idiom 방법으로 SingleTon 구현
public class ShopContainer {

	private ShopContainer() {  // 인스턴스화 방지
	}

	// Order IoC

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

	// Review IoC

	private static class ReviewRepositoryHolder {
		private static final ReviewRepository INSTANCE = new InMemoryReviewRepository();
	}

	public static ReviewRepository reviewRepository() {
		return ReviewRepositoryHolder.INSTANCE;
	}

	private static class ReviewServiceHolder {
		private static final ReviewService INSTANCE = new ReviewService(reviewRepository());
	}

	public static ReviewService reviewService() {
		return ReviewServiceHolder.INSTANCE;
	}

	private static class ReviewControllerHolder {
		private static final ReviewController INSTANCE = new ReviewController(reviewService());
	}

	public static ReviewController reviewController() {
		return ReviewControllerHolder.INSTANCE;
	}

	private static class OrderFacadeControllerHolder {
		private static final ShopFacadeController INSTANCE = new ShopFacadeController(
			orderController(), reviewController()
		);
	}

	public static ShopFacadeController orderFacadeController() {
		return OrderFacadeControllerHolder.INSTANCE;
	}
}
