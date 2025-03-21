package com.example.letstrip.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.letstrip.dao.TimelineDAO;
import com.example.letstrip.dto.TimelineDTO;
import com.example.letstrip.entity.Timeline;

@Service
public class TimelineService {
	@Autowired
	TimelineDAO dao;

	public boolean addTimeline(TimelineDTO dto) {
		return dao.addTimeline(dto);
	}
	
	// 타임라인 보기
	public List<Timeline> findByIdAndName(String plan_name, String id) {
		return dao.findByIdAndName(plan_name, id);
	}
	
	// 타임라인 리스트 조회
	public List<String> findListOrderByTime(String id) {
		return dao.findListOrderByTime(id);
	}
	
	// 타임라인 삭제
	public boolean deleteTimeline(Long timelineSeq) {
		return dao.deleteTimeline(timelineSeq);
	}
	
	// 타임라인 모두 삭제
	@Transactional
	public boolean deleteTimelineAll(String id, String plan_name) {
		return dao.deleteTimelineAll(id, plan_name);
	}
	
}
