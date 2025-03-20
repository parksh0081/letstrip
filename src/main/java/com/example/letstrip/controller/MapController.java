package com.example.letstrip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.example.letstrip.entity.Place;
import com.example.letstrip.entity.Review;
import com.example.letstrip.service.MapReviewService;
import com.example.letstrip.service.PlaceService;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class MapController {

	@Autowired
	PlaceService placeService;
	
	@Autowired
	MapReviewService mapReviewService;
	
	@GetMapping("/map/mapMain")
	public String mapMain() {
		return "/map/mapMain";
	}

	
	@GetMapping("/map/map")
	public String map() {
		return "/map/map";
	}

	@GetMapping("/map/mapPlaceView")
	public String mapPlaceViewHtml(@RequestParam("place_id") String place_id, Model model) {
		// 장소 정보 제공을 위한 
		Place place=placeService.select(place_id);
		// 리뷰 목록 
		List<Review>reviewList=mapReviewService.selectList(place_id);
		
		model.addAttribute("reviewList",reviewList);
		model.addAttribute("place", place);
		model.addAttribute("place_id", place_id);
		return "/map/mapPlaceView";
	}
	
	@GetMapping("/map/mapReviewWriteForm")
	public String mapReviewWriteForm(HttpServletRequest request, Model model) {
		String id=request.getParameter("place_id");
		Place place=placeService.select(id);
		model.addAttribute("place",place);
		return "/map/mapReviewWriteForm";
	}
	
	@GetMapping("/map/updateSideTab")
	public String updateSideTab(Model model, @RequestParam("place_id") String place_id) {
	System.out.println("placeid: "+place_id);
		// 리뷰 목록 
		List<Review>reviewList=mapReviewService.selectList(place_id);
		
		model.addAttribute("reviewList",reviewList);
		System.out.println("List"+reviewList.size());
		//return "map::mapReviewListFragment"; // 프래그먼트 반환 
		return "map/mapPlaceView::reviewListContainer"; // 프래그먼트 반환 
		//return "/map/mapReviewListFragment";
	}
	
}

