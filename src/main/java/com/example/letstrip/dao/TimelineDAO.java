package com.example.letstrip.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.dto.TimelineDTO;
import com.example.letstrip.entity.Timeline;
import com.example.letstrip.repository.TimelineRepository;

@Repository
public class TimelineDAO {
	@Autowired
	TimelineRepository timelineRepository;
	// 타임라인 추가
	public boolean addTimeline(TimelineDTO dto) {
		boolean result = false;
		if(!timelineRepository.existsById(dto.getTimelineSeq())) {
			Timeline timeline = dto.toEntity();
			Timeline timeline_result = timelineRepository.save(timeline);
			if(timeline_result != null) {
				result = true;
			}
		}
		return result;
	}
	// 타임라인 보기
	public List<Timeline> findAllOrderByTime(String id) {
		return timelineRepository.findAllOrderByTime(id);
	}
	
	// 타임라인 삭제
	public boolean deleteTimeline(Long timelineSeq) {
		Timeline timeline = timelineRepository.findById(timelineSeq).orElse(null);
		boolean result = false;
		if(timeline != null) {
			timelineRepository.deleteById(timelineSeq);
			if(!timelineRepository.existsById(timelineSeq)) {
				result = true;
			}
		}
		return result;
	}
}
