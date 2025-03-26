package com.example.letstrip.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.letstrip.dto.ReviewlikeDTO;
import com.example.letstrip.entity.Review;
import com.example.letstrip.entity.Reviewlike;
import com.example.letstrip.repository.ReviewlikeRepository;

@Repository
public class ReviewlikeDAO {
	
	@Autowired
	ReviewlikeRepository repository;
	
	// insert
	public Reviewlike insertReviewlike(ReviewlikeDTO dto) {
		return repository.save(dto.toEntity());
	}
	
	// 조회 
	public Reviewlike selectReviewlike(String person_id, int review_seq) {
		return repository.findByIdAndSeq(person_id,review_seq);
	}
	
	// 삭제
	public int deleteReviewlike(String person_id, int review_seq) {
		return repository.deleteBySeq(person_id, review_seq);
	}
	
	// 사용자별 좋아요 리스트
	public List<Reviewlike>selectListById(String person_id){
		return repository.findAllbyId(person_id);
	}
}
