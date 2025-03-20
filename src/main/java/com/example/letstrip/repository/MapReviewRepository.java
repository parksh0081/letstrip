package com.example.letstrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letstrip.entity.Review;

public interface MapReviewRepository extends JpaRepository<Review, Integer>{
	public List<Review> findByPlaceid(String placeid);
}
