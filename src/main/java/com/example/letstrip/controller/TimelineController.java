package com.example.letstrip.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.letstrip.dto.TimelineDTO;
import com.example.letstrip.entity.Place;
import com.example.letstrip.entity.Review;
import com.example.letstrip.entity.Store;
import com.example.letstrip.entity.Timeline;
import com.example.letstrip.entity.Travelplan;
import com.example.letstrip.service.MapReviewService;
import com.example.letstrip.service.PlaceService;
import com.example.letstrip.service.StoreService;
import com.example.letstrip.service.TimelineService;
import com.example.letstrip.service.TravelplanService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
public class TimelineController {
	@Autowired
	TimelineService timelineService;
	@Autowired
	TravelplanService travelplanService;
	@Autowired
	StoreService storeService;
	@Autowired
	PlaceService placeService;
	@Autowired
	MapReviewService mapReviewService;
	// 메인 화면
	@GetMapping("/planner/myPlanner")
	public String myPlanner(Model model, HttpSession session) {
		String personId = (String) session.getAttribute("personId");
		// 찜 리스트 들고오기 
 		List<Store>storeList=storeService.selectList(personId);
 		if (storeList == null) {  //storeList가 null이라면 빈 리스트로 초기화
 	        storeList = new ArrayList<>();
 	    }
 		for(Store store:storeList) {
 			store.addPlace(placeService.select(store.getPlaceid()));
 		}
 		
 		model.addAttribute("personId", personId);
		model.addAttribute("req", "none");
 		model.addAttribute("storeList",storeList);
		return "planner/myPlanner";
	}
	
	/**
     * 타임라인 화면 (GET) - travelplan.html에서 넘어올 때 startDate, endDate 전달
     */
    @GetMapping("/planner/timeline")
    public String timeline(
        Model model,
        @RequestParam("startDate") String startDate,
        @RequestParam("endDate") String endDate,
        @RequestParam("plan_name") String plan_name,
        HttpSession session
    ) {
        String personId = (String) session.getAttribute("personId");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDateTime = LocalDate.parse(startDate, formatter).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(endDate, formatter).atStartOfDay();
	    // 찜 리스트 들고오기 
  		List<Store>storeList=storeService.selectList(personId);
  		if (storeList == null) {  //storeList가 null이라면 빈 리스트로 초기화
  	        storeList = new ArrayList<>();
  	    }
  		for(Store store:storeList) {
  			store.addPlace(placeService.select(store.getPlaceid()));
  		}
        model.addAttribute("personId", personId);
        model.addAttribute("plan_name", plan_name);
        model.addAttribute("startDate", startDateTime);
        model.addAttribute("endDate", endDateTime);
 		model.addAttribute("storeList",storeList);

        model.addAttribute("req", "/planner/timeline");
        return "planner/myPlanner";
    }
    
    /**
     * 타임라인 화면 (GET) - myPlanner에서 timelineHistory.html으로 이동해서 계획 기록 보기
     */
    @GetMapping("/planner/timelineHistory")
    public String timelineHistory(
    		Model model,
    		@RequestParam("plan_name") String plan_name,
    		HttpSession session
    		) {
    	String personId = (String) session.getAttribute("personId");
    	System.out.println("id = " + personId);
    	List<Timeline> list = timelineService.findByIdAndName(plan_name, personId);

    	// 찜 리스트 들고오기 
 		List<Store>storeList=storeService.selectList(personId);
 		if (storeList == null) {  //storeList가 null이라면 빈 리스트로 초기화
 	        storeList = new ArrayList<>();
 	    }
 		for(Store store:storeList) {
 			store.addPlace(placeService.select(store.getPlaceid()));
 		}
 		
    	model.addAttribute("personId", personId);
    	model.addAttribute("plan_name", plan_name);
    	model.addAttribute("items", list);
    	model.addAttribute("storeList",storeList);
    	model.addAttribute("req", "/planner/timelineHistory");
        return "planner/myPlanner";
    }

    /**
     * 타임라인 추가 화면 (GET) - timeline.html에서 addTimeline.html로 startDate, endDate 전달
     */
    @GetMapping("/planner/addTimeline")
    public String addTimeline(
        Model model,
        @RequestParam("startDate") String startDate,
        @RequestParam("endDate") String endDate,
        @RequestParam("plan_name") String plan_name,
        HttpSession session
    ) {
        String personId = (String) session.getAttribute("personId");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDateTime = LocalDate.parse(startDate, formatter).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(endDate, formatter).atTime(23, 59); // 마지막 날은 하루 끝까지

        model.addAttribute("personId", personId);
        model.addAttribute("startDate", startDateTime);
        model.addAttribute("endDate", endDateTime);
        model.addAttribute("plan_name", plan_name);
		
        return "planner/addTimeline";
    }

    /**
     * 타임라인 저장 (POST) - addTimeline.html에서 입력된 정보를 저장 후 timeline.html로 리다이렉트
     */
    @PostMapping("/planner/timeline")
    @ResponseBody
    public String saveTimeline(Model model, TimelineDTO dto, HttpSession session) {
        String personId = (String) session.getAttribute("personId");
		// 찜 리스트 들고오기 
		List<Store>storeList=storeService.selectList(personId);
		if (storeList == null) {  //storeList가 null이라면 빈 리스트로 초기화
		    storeList = new ArrayList<>();
		}
		for(Store store:storeList) {
			store.addPlace(placeService.select(store.getPlaceid()));
		}
        // Travelplan 객체 생성 및 연결
        Travelplan travelplan = new Travelplan();
        travelplan.setId(personId);
        dto.setTravelplan(travelplan);

        // Timeline 저장
        timelineService.addTimeline(dto);

        // 타임라인을 다시 로드해서 반환
        LocalDateTime startDate = dto.getStart_date();
        LocalDateTime endDate = dto.getEnd_date();
        String plan_name = dto.getPlan_name();
        
        model.addAttribute("plan_name", plan_name);
        model.addAttribute("startDate", startDate);
        model.addAttribute("endDate", endDate);
 		model.addAttribute("storeList",storeList);

        model.addAttribute("req", "/planner/timeline");
        return "planner/myPlanner";
    }

    /**
     * 타임라인 삭제 (GET)
     */
    @GetMapping("/planner/deleteTimeline")
    public String deleteTimeline(
        Model model,
        @RequestParam("timelineSeq") Long timelineSeq,
        HttpSession session
    ) {
        String personId = (String) session.getAttribute("personId");

        boolean result = timelineService.deleteTimeline(timelineSeq);

        model.addAttribute("personId", personId);
        model.addAttribute("result", result);
        
        model.addAttribute("req", "/planner/timeline");
        return "planner/myPlanner";
    }
	
    @GetMapping("/planner/deleteTimelineHistory")
    public String deleteTimelineHistory(
            Model model,
            @RequestParam("plan_name") String plan_name,
            HttpSession session
        ) {
            String personId = (String) session.getAttribute("personId");

            boolean result = timelineService.deleteTimelineAll(personId, plan_name);

            model.addAttribute("personId", personId);
            model.addAttribute("result", result);
            
    		model.addAttribute("req", "none");
            return "planner/myPlanner";
        }
    
    // wishlistdetail
 	@GetMapping("/planner/wishlistDetail")
 	public String wishlistDetail(Model model, HttpSession session, @RequestParam("place_id") String place_id) {
        String personId = (String) session.getAttribute("personId");
        model.addAttribute("personId", personId);
 		// 장소 정보 제공을 위한 
		Place place=placeService.select(place_id);
		// 별점 평균
		double finalStar=mapReviewService.selectAvgStar(place_id);

		model.addAttribute("place", place);
		model.addAttribute("place_id", place_id);
		model.addAttribute("finalStar", finalStar);
 		return "planner/wishlist :: wishDetailContainer";
 	}
    
}
