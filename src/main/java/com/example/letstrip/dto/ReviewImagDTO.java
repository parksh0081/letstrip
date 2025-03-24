package com.example.letstrip.dto;

import com.example.letstrip.entity.Reviewimage;

import lombok.Data;

// 보류 삭제 여부 확인 
@Data
public class ReviewImagDTO {
    private int seq; 	// 순서
	
    private String place_image;	// 파일 이름 

	private int review_seq;
	
	public Reviewimage toEntity() {
		return new Reviewimage(review_seq, place_image, null);
	}

}
