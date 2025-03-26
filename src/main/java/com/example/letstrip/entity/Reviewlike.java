package com.example.letstrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Reviewlike {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REVIEWLIKE_SEQUENCE_GENERATOR")
	@SequenceGenerator(name="REVIEWLIKE_SEQUENCE_GENERATOR", sequenceName = "seq_review", initialValue = 1, allocationSize = 1)
	 private int like_seq; 	// 그냥 순서
	 //private int review_seq; 	// 리뷰 글 번호
	 private String person_id; 	// 사용자 id
	 
	 @ManyToOne
	 @JoinColumn(name="review_seq", nullable = false)
	 private Review review;
}
