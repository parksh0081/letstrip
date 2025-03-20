package com.example.letstrip.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.dto.ReviewDTO;
import com.example.letstrip.entity.Review;
import com.example.letstrip.repository.MapReviewRepository;

@Repository
public class MapReviewDAO {

	@Autowired
	MapReviewRepository repository;
	
	// 리뷰 insert 
	// 리뷰 저장시 리뷰이미지 테이블에 함께 저장됨 
	public Review insert(ReviewDTO dto) {
		return repository.save(dto.toEntity());
	}
	
	// 장소 id별로 리뷰 목록 select
	public List<Review> selectList(String placeid){
		return repository.findByPlaceid(placeid);
	}
}
