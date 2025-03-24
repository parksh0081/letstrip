package com.example.letstrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Themapark {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "THEMAPARK_SEQUENCE_GENERATOR")
	@SequenceGenerator(name="THEMAPARK_SEQUENCE_GENERATOR", sequenceName = "seq_themapark", initialValue = 1, allocationSize = 1)
    private int seq; 	// 글 번호
    private String thema_name; 	// 이름
    private String type; 	// 종류 
    private String addr; 	// 주소 
    private String image1; 		// 이미지
    private String url; 	// 홈페이지 주소  

}
