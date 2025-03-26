package com.example.letstrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.ReviewlikeDAO;
import com.example.letstrip.dto.ReviewlikeDTO;
import com.example.letstrip.entity.Reviewlike;

@Service
public class ReviewlikeService {
	
	@Autowired
	ReviewlikeDAO dao;
	
	// 조회 
	public Reviewlike selectReviewlike(String person_id, int review_seq) {
		return dao.selectReviewlike(person_id, review_seq);
	}
		
	// 삭제
	public int deleteReviewlike(String person_id, int review_seq) {
		return dao.deleteReviewlike(person_id, review_seq);
	}

	// insert
	public Reviewlike insertReviewlike(ReviewlikeDTO dto) {
		return dao.insertReviewlike(dto);
	}
	
	// 사용자별 좋아요 리스트
	public List<Reviewlike>selectListById(String person_id){
		return dao.selectListById(person_id);
	}
}
