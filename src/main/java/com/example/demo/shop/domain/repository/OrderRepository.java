package com.example.demo.shop.domain.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.async.EventConsumer;
import com.example.demo.shop.domain.Order;

public interface OrderRepository extends EventConsumer<Order> {

	// void save(String username, Order order);

	Optional<List<Order>> findByUsername(String username);
}
