package com.example.letstrip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
		// 별점 평균
		double finalStar=mapReviewService.selectAvgStar(place_id);
		// 총 리뷰 개수
		int totalReview=mapReviewService.selectCountReview(place_id);
		// 각 별점 평균 
		List<Map<String, Object>> listStar=mapReviewService.selectRatioStar(place_id);

		model.addAttribute("reviewList",reviewList);
		model.addAttribute("place", place);
		model.addAttribute("place_id", place_id);
		model.addAttribute("finalStar", finalStar);
		model.addAttribute("totalReview",totalReview);
		model.addAttribute("listStar",listStar);
		
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
		// 리뷰 목록 
		List<Review>reviewList=mapReviewService.selectList(place_id);
		// 별점 평균
		double finalStar=mapReviewService.selectAvgStar(place_id);
		// 총 리뷰 개수
		int totalReview=mapReviewService.selectCountReview(place_id);
		// 각 별점 평균 
		List<Map<String, Object>>listStar=mapReviewService.selectRatioStar(place_id);
				
		// [{star=5, star_ratio=0.40}, {star=4, star_ratio=0.30}, {star=3, star_ratio=0.20}, {star=2, star_ratio=0.10}]

		model.addAttribute("reviewList",reviewList);
		model.addAttribute("finalStar", finalStar);
		model.addAttribute("totalReview",totalReview);
		model.addAttribute("listStar",listStar);
		
		return "map/mapPlaceView::reviewListContainer"; // 프래그먼트 반환 
		//return "/map/mapReviewListFragment";
	}
//	
	@GetMapping("/map/updateStarRating")
	public String updateStarRating(Model model, @RequestParam("place_id") String place_id) {
		// 별점 평균
		double finalStar=mapReviewService.selectAvgStar(place_id);
		// 총 리뷰 개수
		int totalReview=mapReviewService.selectCountReview(place_id);
		// 각 별점 평균 
		List<Map<String, Object>>listStar=mapReviewService.selectRatioStar(place_id);
			
		model.addAttribute("finalStar", finalStar);
		model.addAttribute("totalReview",totalReview);
		model.addAttribute("listStar",listStar);
		
		return "map/mapPlaceView::starContainer"; // 프래그먼트 반환 
		}
	
}

