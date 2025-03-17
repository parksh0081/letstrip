package com.example.letstrip.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.*;

import com.example.letstrip.dto.TimelineDTO;
import com.example.letstrip.entity.Timeline;
import com.example.letstrip.service.TimelineService;

import jakarta.servlet.http.HttpSession;

@RestController
public class TimelineRestController {
	@Autowired
	TimelineService service;
	
	@GetMapping("/planner/timelineJson")
	public Map<String, Object> Timeline(HttpSession session){
		String personId = (String) session.getAttribute("personId");
		List<Timeline> list = service.findAllOrderByTime(personId);
		
		Map<String, Object> map = new HashMap<>();
		map.put("rt", "OK");
		map.put("total", list.size());
		map.put("items", list);
		map.put("id", personId);
		return map;
	}
	
	@PostMapping("/api/save-data")
    public String saveData(@RequestBody TimelineDTO dto) {
        boolean result = service.addTimeline(dto);
        if (result) {
            return "{\"status\":\"success\", \"message\":\"Data saved successfully\"}";
        } else {
            return "{\"status\":\"error\", \"message\":\"Error saving data\"}";
        }
    }    
}
