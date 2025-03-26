package com.example.letstrip.dto;

import com.example.letstrip.entity.Review;
import com.example.letstrip.entity.Reviewlike;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
public class ReviewlikeDTO {
    private int like_seq; 	// 그냥 순서
    private int review_seq; 	// 리뷰 글 번호
    private String person_id; 	// 사용자 id
    
    private Review review;
    
    public Reviewlike toEntity() {
    	return new Reviewlike(like_seq, person_id, review);
    }

}
