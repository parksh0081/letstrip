package com.example.letstrip.service;

import java.util.List;

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
	
	// 장소 id별로 리뷰 목록 select
	public List<Review> selectList(String place_id){
		return dao.selectList(place_id);
	}
}
