package com.example.letstrip.dto;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.example.letstrip.entity.Timeline;
import com.example.letstrip.entity.Travelplan;

import lombok.Data;

@Data
public class TimelineDTO {

	private Long timelineSeq;  // 타임라인 시퀀스 (NUMBER)    
    private Travelplan travelplan;  // 연관된 travel_plan 객체
	private LocalDateTime start_date;   // 여행 시작일
    private LocalDateTime end_date;   // 여행 종료일
	private LocalDateTime datetime;	// 여행 일정(시간)
    private String title;  // 일정 제목
    private String memo;  // 일정 메모
    
    public Timeline toEntity() {
    	return new Timeline(timelineSeq, travelplan, start_date, end_date, datetime, title, memo);
    }
}
