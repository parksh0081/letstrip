package com.example.letstrip.service;

import java.util.List;
import java.util.Map;

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
	
	// 총 별점 평균 
	public double selectAvgStar(String placeid) {
		return dao.selectAvgStar(placeid);
	}
	
	// 각 별점 비율
	public List<Map<String, Object>> selectRatioStar(String placeid) {
		return dao.selectRatioStar(placeid);
	}
	
	// 총 리뷰 수 
	public int selectCountReview(String placeid) {
		return dao.selectCountReview(placeid);
	}
}
