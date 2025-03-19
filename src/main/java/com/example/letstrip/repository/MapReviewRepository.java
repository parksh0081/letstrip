package com.example.letstrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letstrip.entity.Review;

public interface MapReviewRepository extends JpaRepository<Review, Integer>{

}
