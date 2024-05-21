package com.example.demo.order.infrastructure;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.example.demo.order.domain.Order;
import com.example.demo.order.domain.repository.OrderRepository;

public class InMemoryOrderRepository implements OrderRepository {

	// Table 이 정의되지 않아서 일단 username 을 key 값으로 하는 map 을 사용했습니다.
	private final Map<String, List<Order>> orderMap = new ConcurrentHashMap<>();

	@Override
	public void save(String username, Order order) {
		List<Order> orders = orderMap.computeIfAbsent(
			username, k -> new ArrayList<>()
		);
		orders.add(order);
	}

	@Override
	public Optional<List<Order>> findByUsername(String username) {
		return Optional.ofNullable(orderMap.get(username));
	}
}
