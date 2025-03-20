package com.example.letstrip.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.example.letstrip.entity.Review;
import com.example.letstrip.entity.Reviewimage;

import lombok.Data;

@Data 
public class ReviewDTO { 	// 데이터 전송을 위한 객체임 
    private int seq;  // 리뷰 글 번호  
    private String id; 	//사용자 id (중복 작성 가능) 
    private String placeid; 	// 장소 id
    private String content; 	// 리뷰 내용 
    private int star;	// 별점 1~5 정수만 가능 
    private int react; 	//Ω 사람들의 추천 수(좋아요 수)
    private Date logtime;    // 리뷰 작성 시
    
    private List<String> images=new ArrayList<>(); // 이미지 url 
    
    public Review toEntity() {
    	Review review = new Review();
        review.setSeq(seq);
        review.setId(id);
        review.setPlaceid(placeid);
        review.setContent(content);
        review.setStar(star);
        review.setReact(react);
        review.setLogtime(logtime);
        
	    List<Reviewimage>reviewimages=images.stream().map(url->{  // List<String>을 스트림으로 변환 후 이미지 url을 객체로 변환 
	    	Reviewimage reviewimage=new Reviewimage();
	    	reviewimage.setPlace_image(url);
	    	reviewimage.setReview(review);
	    	return reviewimage;
	    }).collect(Collectors.toList()); // List<ReviewImage> 로 변환 
    	
	    review.setImages(reviewimages);
	    return review;
    }

}
