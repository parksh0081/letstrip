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
	public String mateboardView(@RequestParam("pg") int pg, @RequestParam("seq") int seq, Model model) {
		Mateboard mateboard = service.mateboardView(seq);
		
		// 조회수 업데이트
		service.updateView(seq);
		
		model.addAttribute("pg", pg);
		model.addAttribute("seq", seq);
		model.addAttribute("mateboard", mateboard);
		
		return "/mateboard/mateboardView";
	}	
	
	// 글 삭제하기
	@GetMapping("/mateboard/mateboardDelete")
	public String mateboardDelete(@RequestParam("pg") int pg, @RequestParam("seq") int seq, Model model) {
		boolean result = service.mateboardDelete(seq);
		
		model.addAttribute("pg", pg);
		model.addAttribute("seq", seq);
		model.addAttribute("result", result);
		
		return "/mateboard/mateboardDelete";
	}
	
	// 글 수정
	@GetMapping("/mateboard/mateboardModifyForm")
	public String mateboardModifyForm(@RequestParam("pg") int pg, @RequestParam("seq") int seq, Model model) {
		// 한 게시글 정보 
		Mateboard mateboard = service.mateboardView(seq);
		
		model.addAttribute("pg", pg);
		model.addAttribute("seq", seq);
		model.addAttribute("mateboard", mateboard);		
		
		return "/mateboard/mateboardModifyForm";
	}	
	
	// 글 수정 결과
	@PostMapping("/mateboard/mateboardModify")
	public String mateboardModify(Model model, MateboardDTO dto, @RequestParam("pg") int pg, @RequestParam("seq") int seq, HttpSession session) {
		dto.setId((String)session.getAttribute("personId"));
		dto.setName((String)session.getAttribute("personName"));
		
		// 날짜는 그대로 
	    Mateboard oldMateboard = service.mateboardView(seq); 
	    if (oldMateboard != null) {
	        dto.setLogtime(oldMateboard.getLogtime()); 
	    }		
		boolean result = service.mateboardModify(dto);
			
		model.addAttribute("pg", pg);
		model.addAttribute("seq", seq);
		model.addAttribute("result", result);
		
		return "/mateboard/mateboardModify";
	}	
	
}