package com.example.letstrip.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.entity.Timeline;
import com.example.letstrip.entity.Travelplan;
import com.example.letstrip.repository.TravelplanRepository;

@Repository
public class TravelplanDAO {
	@Autowired
	TravelplanRepository travelplanRepository;
	
	// travelplan이 계정에 생성되어 있는지 확인
	public Travelplan findTPById(String id) {
		return travelplanRepository.findById(id).orElse(null);
	}
	
	// 여행 계획 생성
	public Travelplan createTravelPlan(String id) {
	    // 먼저 userId로 여행 계획이 이미 존재하는지 확인
	    Travelplan existplan = travelplanRepository.findById(id).orElse(null);

	    if (existplan != null) {
	        
	        return travelplanRepository.save(existplan);  // 기존 여행 계획 업데이트
	    } else {
	        // 존재하지 않으면 새로운 TravelPlan 생성
	        Travelplan travelplan = new Travelplan();
	        travelplan.setId(id);  // userId를 TravelPlan의 id로 설정
	        travelplan.setTimelines(new ArrayList<>());  // 비어있는 타임라인 리스트 설정

	        return travelplanRepository.save(travelplan);  // 새로운 여행 계획 저장
	    }
	}


}
