package com.example.letstrip.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class MainController {
	@GetMapping("/index")
	public String index(HttpSession session, Model model) {
	    String memId = (String) session.getAttribute("memId");
	    model.addAttribute("memId", memId);
	    return "/main/index";
	}
	
	@GetMapping("/poster/seoul")
	public String seoulPoster() {
		return "/poster/seoul";
	}
	@GetMapping("/poster/busan")
	public String busanPoster() {
		return "/poster/busan";
	}
	@GetMapping("/poster/gangneung")
	public String gangneungPoster() {
		return "/poster/gangneung";
	}
	@GetMapping("/poster/geongju")
	public String geongjuPoster() {
		return "/poster/geongju";
	}
	@GetMapping("/poster/jeju")
	public String jejuPoster() {
		return "/poster/jeju";
	}
	@GetMapping("/poster/jeonju")
	public String jeonjuPoster() {
		return "/poster/jeonju";
	}
	
}
