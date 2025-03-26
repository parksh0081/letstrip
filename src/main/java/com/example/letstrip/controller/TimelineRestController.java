package com.example.letstrip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.example.letstrip.dto.TimelineDTO;
import com.example.letstrip.entity.Timeline;
import com.example.letstrip.entity.Travelplan;
import com.example.letstrip.service.TimelineService;
import com.example.letstrip.service.TravelplanService;

import jakarta.servlet.http.HttpSession;

@RestController
public class TimelineRestController {
	@Autowired
	TimelineService service;
	@Autowired
	TravelplanService travelplanService;
	
	@GetMapping("/planner/timelineJson")
	public Map<String, Object> Timeline(HttpSession session, @RequestParam("plan_name") String plan_name){
		String personId = (String) session.getAttribute("personId");
		List<Timeline> list = service.findByIdAndName(plan_name, personId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("rt", "OK");
		map.put("total", list.size());
		map.put("id", personId);
		map.put("plan_name", plan_name);
		map.put("items", list);
		return map;
	}
	
	@GetMapping("/planner/tlnameJson")
	public Map<String, Object> tlname(HttpSession session){
		String personId = (String) session.getAttribute("personId");
		List<String> list = service.findListOrderByTime(personId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("rt", "OK");
		map.put("total", list.size());
		map.put("id", personId);
		map.put("items", list);
		return map;
	}
	
	
	@PostMapping("/api/save-data")
    public ResponseEntity<?> saveData(@RequestBody TimelineDTO dto, HttpSession session) {
		String personId = (String) session.getAttribute("personId");

	    // Travelplan을 가져와서 할당
	    Travelplan travelplan = travelplanService.findTPById(personId);
	    dto.setTravelplan(travelplan);
	    
        boolean result = service.addTimeline(dto);
        if (result) {
        	return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to save timeline.");
        }
    }    
}
