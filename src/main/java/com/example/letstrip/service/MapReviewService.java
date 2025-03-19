package com.example.letstrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.MapReviewDAO;
import com.example.letstrip.dto.ReviewDTO;
import com.example.letstrip.entity.Review;

@Service
public class MapReviewService {
	
	@Autowired
	MapReviewDAO dao;
	
	// 리뷰 insert 
	public Review insert(ReviewDTO dto) {
		return dao.insert(dto);
	}
}
