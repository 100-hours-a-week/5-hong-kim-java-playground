package com.example.demo.shop.infrastructure;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

import com.example.demo.shop.domain.Review;
import com.example.demo.shop.domain.repository.ReviewRepository;

public class InMemoryReviewRepository implements ReviewRepository {

	private final Map<String, Review> reviewMap = new ConcurrentHashMap<>();

	@Override
	public void save(Review review) {
		String username = review.getUsername();
		reviewMap.put(username, review);
	}

	@Override
	public Optional<Review> findByUsername(String username) {
		return Optional.ofNullable(reviewMap.get(username));
	}

	@Override
	public Optional<List<Review>> findAll() {
		return Optional.of(reviewMap.values()
			.stream()
			.toList());
	}
}
