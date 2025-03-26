package com.example.letstrip.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.letstrip.dto.StoreDTO;
import com.example.letstrip.entity.Place;
import com.example.letstrip.entity.Review;
import com.example.letstrip.entity.Reviewlike;
import com.example.letstrip.entity.Store;
import com.example.letstrip.service.MapReviewService;
import com.example.letstrip.service.PlaceService;
import com.example.letstrip.service.ReviewlikeService;
import com.example.letstrip.service.StoreService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class MapController {

	@Autowired
	PlaceService placeService;
	
	@Autowired
	MapReviewService mapReviewService;
	
	@Autowired
	StoreService storeService;
	
	@Autowired
	ReviewlikeService reviewlikeService;
	
	@GetMapping("/map/mapMain")
	public String mapMain() {
		return "/map/mapMain";
	}

	
	@GetMapping("/map/map")
	public String map() {
		return "/map/map";
	}

	@GetMapping("/map/mapPlaceView")
	public String mapPlaceViewHtml(@RequestParam("place_id") String place_id, Model model, HttpServletRequest request) {
		
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

		
		model.addAttribute("place", place);
		model.addAttribute("place_id", place_id);
		model.addAttribute("finalStar", finalStar);
		model.addAttribute("totalReview",totalReview);
		model.addAttribute("listStar",listStar);
		model.addAttribute("reviewList", reviewList);
		
//		// 좋아요 상태 추가 
//		HttpSession session=request.getSession();
//		String personId=(String) session.getAttribute("personId");
//		Set<Integer>likeReviewSeqs=new HashSet<>();
//		if(personId!=null) {
//			// 누른 좋아요 리스트 들고오기 
//			List<Reviewlike>likeList=reviewlikeService.selectListById(personId);
//			for (Reviewlike like : likeList) {
//				likeReviewSeqs.add(like.getReview().getSeq()); 
//			}
//		}
//			// 각 리뷰에 사용자의 좋아요 상태 추가
//			List<Map<String, Object>> reviewWithLikes = new ArrayList<>();
//			for (Review review : reviewList) {
//				Map<String, Object> reviewData = new HashMap<>();
//				reviewData.put("review", review);
//				reviewData.put("liked", likeReviewSeqs.contains(review.getSeq())); // 좋아요 여부 추가
//				reviewWithLikes.add(reviewData);
//			}			
//		
//		model.addAttribute("reviewList", reviewWithLikes);
		
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
	
	// ** 내 찜 사이드탭
	@GetMapping("/map/mapPlaceStoreTab")
	public String mapPlaceStoreTab(Model model, @RequestParam("id") String id) {
		// 찜 리스트 들고오기 
		List<Store>storeList=storeService.selectList(id);
		if (storeList == null) {  //storeList가 null이라면 빈 리스트로 초기화
	        storeList = new ArrayList<>();
	    }
		for(Store store:storeList) {
			store.addPlace(placeService.select(store.getPlaceid()));
		}
				
		model.addAttribute("storeList",storeList);
		return "/map/mapPlaceStoreTab";
	}
	
	

}

