package com.example.letstrip.controller;

import com.example.letstrip.entity.Festival;
import com.example.letstrip.service.FestivalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FestivalController {
	@Autowired
    FestivalService festivalService;

	@GetMapping("/festival")
	public String festivals() {
		return "festival/festival";
	}
	
    @GetMapping("/festival/searchFestivals")
    public String searchFestivals(@RequestParam(name = "areas", required = false) List<String> areas,
					              @RequestParam(name = "months", required = false) List<String> months,
					              @RequestParam(name = "name", required = false, defaultValue = "") String name,
                                  @RequestParam(name = "page", defaultValue = "0") int page, Model model) {
    	// 빈 리스트를 null로 처리
        if (areas != null && areas.isEmpty()) areas = null;
        if (months != null && months.isEmpty()) months = null;
        
        Pageable pageable = PageRequest.of(page, 9);  // Display 9 festivals per page
        Page<Festival> festivalPage = festivalService.getFestivalsByFilters(areas, months, name, pageable);

        model.addAttribute("festivalPage", festivalPage);
        model.addAttribute("page", page);
        model.addAttribute("totalPages", festivalPage.getTotalPages());
        model.addAttribute("selectedAreas", areas);
        model.addAttribute("selectedMonths", months);
        model.addAttribute("searchName", name);
        return "festival/festival";  // Thymeleaf template for displaying festivals
    }
}
