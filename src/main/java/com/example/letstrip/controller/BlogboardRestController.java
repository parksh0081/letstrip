package com.example.letstrip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.letstrip.entity.Blogboard;
import com.example.letstrip.service.BlogboardService;


@RestController
public class BlogboardRestController {
	@Autowired
	BlogboardService service;
	
	// http://localhost:8080/blogboard/blogboardListJson?pg=1
	@GetMapping("blogboard/blogboardListJson")
	public Map<String, Object> boardList(@RequestParam(value = "pg", defaultValue = "1") int pg) {
		System.out.println("pg = " + pg);
		// 1. 데이터 처리
		// 목록 : 5개
		int endNum = pg * 5;
		int startNum = endNum - 4;
		List<Blogboard> list = service.blogboardList(startNum, endNum);
		
		// 페이징 : 3블럭
		int totalA = service.getCount();	// 총 글 수
		int totalP = (totalA + 4) / 5;		// 총 페이지 수
		
		int startPage = (pg-1)/3*3 + 1;
		int endPage = startPage + 2;
		if(endPage > totalP) endPage = totalP;
		
		// 2. 결과 : json 데이터 생성 준비
		Map<String, Object> map = new HashMap<>();
		map.put("rt", "OK");
		map.put("total", list.size());
		map.put("pg", pg);
		map.put("totalP", totalP);
		map.put("startPage", startPage);
		map.put("endPage", endPage);
		map.put("blogboard", list);
		
		//System.out.println("Response data: " + map);  // 응답 데이터 확인
		return map;
	}
}
