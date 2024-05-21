package com.example.demo.order.domain.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.order.domain.Order;

public interface OrderRepository {

	void save(String username, Order order);

	Optional<List<Order>> findByUsername(String username);
}
