package com.example.letstrip.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.example.letstrip.entity.Review;
import com.example.letstrip.entity.Reviewimage;

import lombok.Data;

@Data 
public class ReviewDTO { 	// 데이터 전송을 위한 객체임 
    private int seq;  // 리뷰 글 번호  
    private String id; 	//사용자 id (중복 작성 가능) 
    private String place_id; 	// 장소 id
    private String content; 	// 리뷰 내용 
    private int star;	// 별점 1~5 정수만 가능 
    private int react; 	//Ω 사람들의 추천 수(좋아요 수)
    private Date logtime;    // 리뷰 작성 시
    private List<Reviewimage> images=new ArrayList<>();
    
    public Review toEntity() {
    	return new Review(seq, id, place_id, content, star, react, logtime, images);
    }

}
