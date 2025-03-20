package com.example.letstrip.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Review {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEW_SEQUENCE_GENERATOR")
	@SequenceGenerator(name="REVIEW_SEQUENCE_GENERATOR", sequenceName = "seq_review", initialValue = 1, allocationSize = 1)
    private int seq;  // 리뷰 글 번호  
    private String id; 	//사용자 id (중복 작성 가능) 
    private String placeid; 	// 장소 id
    private String content; 	// 리뷰 내용 
    private int star;	// 별점 1~5 정수만 가능 
    private int react; 	//Ω 사람들의 추천 수(좋아요 수)
    @Temporal(TemporalType.DATE)
    private Date logtime;    // 리뷰 작성 시
    
    @OneToMany(mappedBy="review", cascade = CascadeType.ALL) 	// reviewImage 와 1:N 관계 설정 
    private List<Reviewimage> images=new ArrayList<>();
    
    // getter setter
    public void addImage(Reviewimage image) { // 비즈니스 로직 
    	images.add(image);
    	image.setReview(this); 	// 양방향 관계 설정 
    }
}
