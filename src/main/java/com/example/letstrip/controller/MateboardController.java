package com.example.letstrip.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.letstrip.dto.MateboardDTO;
import com.example.letstrip.entity.Mateboard;
import com.example.letstrip.service.MateboardService;

import jakarta.servlet.http.HttpSession;

@Controller
public class MateboardController {
	
	@Autowired
	MateboardService service;
	
	// 동행 구하기 게시판 리스트(동적으로, 검색 기능 추가)
	@GetMapping("/mateboard/mateboardList")
	public String mateboardList(Model model, @RequestParam(value = "pg", defaultValue = "1")int pg) {
		model.addAttribute("pg", pg);
		// 페이징 처리
		return "/mateboard/mateboardList";
	}
	
	// 글쓰기
	@GetMapping("/mateboard/mateboardWriteForm")
	public String mateboardWriteForm() {
		return "/mateboard/mateboardWriteForm";
	}
	
	// 글쓰기 결과
	@PostMapping("/mateboard/mateboardWrite")
	public String mateboardWrite(Model model, MateboardDTO dto, HttpSession session) {
		dto.setId((String)session.getAttribute("personId"));
		dto.setName((String)session.getAttribute("personName"));
		dto.setLogtime(new Date());
		
		// db
		Mateboard mateboard = service.mateboardWrite(dto);
		boolean result = false;
		if(mateboard != null) {
			result = true;
		}
		
		// 2. 데이터 공유
		model.addAttribute("result", result);
		// 3. view 파일명 리턴
		return "/mateboard/mateboardWrite";
	}	
	
	// 글 상세보기
	@GetMapping("/mateboard/mateboardView")
	public String mateboardView() {
		return "/mateboard/mateboardView";
	}	
	
}