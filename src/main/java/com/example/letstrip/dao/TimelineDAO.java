package com.example.letstrip.dao;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

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
		Timeline timeline = dto.toEntity();
		Timeline timeline_result = timelineRepository.save(timeline);
		if(timeline_result != null) {
			result = true;
		}
		return result;
	}
	// 타임라인 보기
	public List<Timeline> findByIdAndName(String plan_name, String id) {
		return timelineRepository.findByIdAndName(plan_name, id);
	}

	// 타임라인 기록 조회
	public List<String> findListOrderByTime(String id) {
		Set<String> set_list = timelineRepository.findListOrderByTime(id);
		return new ArrayList<>(set_list);
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
	
	// 타임라인 모두 삭제
	public boolean deleteTimelineAll(String id, String plan_name) {
		List<Timeline> list = timelineRepository.findByIdAndName(plan_name, id);
		boolean result = false;
		if(list != null) {
			timelineRepository.deleteList(id, plan_name);
			if(timelineRepository.findByIdAndName(plan_name, id).isEmpty()) {
				result = true;
			}
		}
		return result;
	}
}
