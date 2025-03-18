package com.example.letstrip.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.letstrip.dto.TravelplanDTO;
import com.example.letstrip.entity.Timeline;
import com.example.letstrip.entity.Travelplan;
import com.example.letstrip.service.TravelplanService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class TravelplanController {
	@Autowired
	TravelplanService service;
	
	@GetMapping("/planner/travelplan")
	public String travelplan(Model model, HttpServletRequest request, HttpSession session) {
		String personId = (String) session.getAttribute("personId");
		TravelplanDTO dto = new TravelplanDTO();
		dto.setId(personId);
		
		service.createTravelPlan(personId);

		model.addAttribute("personId", personId);
		model.addAttribute("req", "/planner/travelplan");
		return "/planner/myPlanner";
	}
}
