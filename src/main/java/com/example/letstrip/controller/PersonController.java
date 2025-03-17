package com.example.letstrip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.letstrip.dto.PersonDTO;
import com.example.letstrip.entity.Person;
import com.example.letstrip.service.PersonService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class PersonController {
	@Autowired
	PersonService service;

	// 회원 가입 창
	@GetMapping("/personWriteForm")
	public String personWriteForm() {
		return "/person/personWriteForm";
	}

	// 회원 가입
	@PostMapping("/personWrite")
	public String personWrite(Model model, PersonDTO dto) {
		Person person = service.personWrite(dto);
		model.addAttribute("person", person);
		return "/person/personWrite";
	}

	// 회원 정보 수정 폼
	@GetMapping("/personModifyForm")
	public String modifyForm(HttpServletRequest request, Model model) {
		// 1. 데이터 처리
		HttpSession session = request.getSession();
		String id = (String)session.getAttribute("personId");
		// db
		Person person = service.getPerson(id);
		// 2. 데이터 공유
		model.addAttribute("person", person);
		model.addAttribute("id", id);
		// 3. view 파일명 리턴
		return "/person/personModifyForm";
	}

	// 회원 정보 수정
	@PostMapping("/personModify")
	public String modify(Model model, PersonDTO dto) {
		// 1. 데이터 처리
		// db
		boolean result = service.personModify(dto);
		// 2. 데이터 공유
		model.addAttribute("result", result);
		// 3. view 파일명 리턴
		return "/person/personModify";
	}

	// 로그인
	@GetMapping("/personLoginForm")
	public String loginForm() {
		return "/person/personLoginForm";
	}

	@PostMapping("/personLogin")
	public String login(HttpServletRequest request, Model model) {
		boolean result = false;
		HttpSession session = request.getSession();
		String id = request.getParameter("id");
		String pwd = request.getParameter("pwd");

		Person person = service.login(id, pwd);
		if (person != null) { // 로그인 성공
			session.setAttribute("personId", id);
			session.setAttribute("personName", person.getName());
			result = true;
		}
		model.addAttribute("result", result);

		return "/person/personLogin";
	}
	
	// 로그아웃
	@GetMapping("/psersonLogout")
	public String logout(HttpSession session) {
		// 1. 데이터 처리
		session.removeAttribute("personName");
		session.removeAttribute("personId");
		// 세션 무효화 : 모두 지우기
		//session.invalidate();
		// 2. 데이터 공유
		// 3. view 파일명 리턴
		return "/person/personLogout";
	}	
	
	// 마이페이지
	@GetMapping("/personPage")
	public String personPage() {
		// 1. 데이터 처리
		// 2. 데이터 공유
		// 3. view 파일명 리턴
		return "/person/personPage";
	}	
}
