package com.example.letstrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MateboardController {
	
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

}