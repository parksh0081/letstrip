package com.example.letstrip.dto;

import com.example.letstrip.entity.Themapark;

import lombok.Data;

@Data
public class ThemaparkDTO {
    private int seq; 	// 글 번호
    private String thema_name; 	// 이름
    private String type; 	// 종류 
    private String addr; 	// 주소 
    private String image1; 		// 이미지
    private String url; 	// 홈페이지 주소  
    
    public Themapark toEntity() {
    	return new Themapark(seq, thema_name, type, addr, image1, url);
    }
}
