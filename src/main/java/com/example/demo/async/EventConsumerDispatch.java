package com.example.demo.async;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.example.demo.common.domain.BaseTimeEntity;
import com.example.demo.member.MemberContainer;
import com.example.demo.member.domain.Member;
import com.example.demo.shop.ShopContainer;
import com.example.demo.shop.domain.Review;
import com.example.demo.shop.domain.keyboard.Keyboard;
import com.example.demo.shop.domain.laptop.Laptop;
import com.example.demo.shop.domain.monitor.Monitor;

public class EventConsumerDispatch {

	private static final Map<Class<? extends BaseTimeEntity>, EventConsumer<? extends BaseTimeEntity>> handlers =
		new ConcurrentHashMap<>();

	static {
		handlers.put(Member.class, MemberContainer.memberRepository());
		handlers.put(Monitor.class, ShopContainer.orderRepository());
		handlers.put(Laptop.class, ShopContainer.orderRepository());
		handlers.put(Keyboard.class, ShopContainer.orderRepository());
		handlers.put(Review.class, ShopContainer.reviewRepository());
	}

	public static <T> Optional<EventConsumer<? extends BaseTimeEntity>> findFitConsumer(Class<T> clazz) {
		return Optional.ofNullable(handlers.get(clazz));
	}
}
