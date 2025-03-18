package com.example.letstrip.entity;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Timeline {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "timeline_seq")
    @SequenceGenerator(name = "timeline_seq", sequenceName = "timeline_seq", allocationSize = 1)
    @Column(name = "timeline_seq")
    private Long timelineSeq;  // 타임라인 시퀀스 (NUMBER)
    
	@ManyToOne
    @JoinColumn(name = "id", nullable = false)
	@JsonBackReference  // 무한참조 방지
    private Travelplan travelplan;  // 연관된 travel_plan 객체
	
	@Column(name = "start_date", nullable = false)
	private LocalDateTime start_date;   // 여행 시작일
	@Column(name = "end_date", nullable = false)
    private LocalDateTime end_date;   // 여행 종료일
	
	@Column(name = "datetime", nullable = false)
    private LocalDateTime datetime;	// 여행 일정(시간)
    
	@Column(name = "title", nullable = false)
    private String title;  // 일정 제목
    
	private String memo;  // 일정 메모
}
