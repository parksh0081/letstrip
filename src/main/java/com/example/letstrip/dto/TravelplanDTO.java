package com.example.letstrip.dto;

import java.util.List;

import com.example.letstrip.entity.Timeline;
import com.example.letstrip.entity.Travelplan;

import lombok.Data;

@Data
public class TravelplanDTO {
	private String id;  // 사용자 ID (PK)
    private List<Timeline> timelines;  // 여행 일정 리스트 (1:N 관계)
	
    public Travelplan toEntity() {
    	return new Travelplan(id, timelines);
    }
}
