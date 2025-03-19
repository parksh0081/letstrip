package com.example.letstrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reviewimage {
	@Id
    private int seq; 	// 순서
	
    private String place_image;	// 파일 이름, 경로   
	
	@ManyToOne 	// 여러 개의 reviewImage 가 하나의 review 와 연결됨 
	@JoinColumn(name="review_seq") // 외래키, review 테이블과 관계 설정  
	private Review review;
}
