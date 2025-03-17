package com.example.letstrip.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	public List<Timeline> findAllOrderByTime(String id) {
		return dao.findAllOrderByTime(id);
	}
	
	// 타임라인 삭제
	public boolean deleteTimeline(Long timelineSeq) {
		return dao.deleteTimeline(timelineSeq);
	}
}
