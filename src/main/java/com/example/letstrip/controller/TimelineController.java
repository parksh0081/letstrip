package com.example.letstrip.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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
import com.example.letstrip.entity.Timeline;
import com.example.letstrip.entity.Travelplan;
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
	// 메인 화면
	@GetMapping("/planner/myPlanner")
	public String myPlanner(Model model, HttpServletRequest request, HttpSession session) {
		String personId = (String) session.getAttribute("personId");
		model.addAttribute("personId", personId);
		return "/planner/myPlanner";
	}
	
	/**
     * 타임라인 화면 (GET) - travelplan.html에서 넘어올 때 startDate, endDate 전달
     */
    @GetMapping("/planner/timeline")
    public String timeline(
        Model model,
        @RequestParam("startDate") String startDate,
        @RequestParam("endDate") String endDate,
        HttpSession session
    ) {
        String personId = (String) session.getAttribute("personId");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDateTime = LocalDate.parse(startDate, formatter).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(endDate, formatter).atStartOfDay();

        model.addAttribute("personId", personId);
        model.addAttribute("startDate", startDateTime);
        model.addAttribute("endDate", endDateTime);

        return "/planner/timeline";
    }

    /**
     * 타임라인 추가 화면 (GET) - timeline.html에서 addTimeline.html로 startDate, endDate 전달
     */
    @GetMapping("/planner/addTimeline")
    public String addTimeline(
        Model model,
        @RequestParam("startDate") String startDate,
        @RequestParam("endDate") String endDate,
        HttpSession session
    ) {
        String personId = (String) session.getAttribute("personId");

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime startDateTime = LocalDate.parse(startDate, formatter).atStartOfDay();
        LocalDateTime endDateTime = LocalDate.parse(endDate, formatter).atStartOfDay();

        model.addAttribute("personId", personId);
        model.addAttribute("startDate", startDateTime);
        model.addAttribute("endDate", endDateTime);

        return "/planner/addTimeline";
    }

    /**
     * 타임라인 저장 (POST) - addTimeline.html에서 입력된 정보를 저장 후 timeline.html로 리다이렉트
     */
    @PostMapping("/planner/timeline")
    public String saveTimeline(
        Model model,
        @ModelAttribute TimelineDTO dto,
        HttpSession session
    ) {
        String personId = (String) session.getAttribute("personId");

        // Travelplan 객체 생성 및 연결
        Travelplan travelplan = new Travelplan();
        travelplan.setId(personId);
        dto.setTravelplan(travelplan);

        // Timeline 저장
        timelineService.addTimeline(dto);

        model.addAttribute("personId", personId);
        return "redirect:/planner/timeline?startDate=" + dto.getStart_date() + "&endDate=" + dto.getEnd_date();
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
        return "/planner/deleteTimeline";
    }
	
}
