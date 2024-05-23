package com.example.demo.shop.domain.repository;

import java.util.List;
import java.util.Optional;

import com.example.demo.shop.domain.Review;

public interface ReviewRepository {

	void save(Review review);

	Optional<Review> findByUsername(String username);

	Optional<List<Review>> findAll();
}
