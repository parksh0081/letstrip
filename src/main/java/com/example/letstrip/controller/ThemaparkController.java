package com.example.letstrip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.letstrip.entity.Themapark;
import com.example.letstrip.service.ThemaparkService;

@Controller
public class ThemaparkController {
	
	@Autowired
	ThemaparkService service;
	
	@GetMapping("/themapark/themapark")
	public String themapark(Model model) {
		List<Themapark> list=service.selectList();
		System.out.println("list: "+list);
		model.addAttribute("list",list);
		return "/themapark/themapark";
	}

}
