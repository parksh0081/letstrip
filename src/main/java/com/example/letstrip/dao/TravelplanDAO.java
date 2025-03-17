package com.example.letstrip.dao;

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
    public Travelplan createTravelPlan(String id, List<Timeline> timelines) {
        // 먼저 userId로 여행 계획이 이미 존재하는지 확인
        Travelplan existplan = travelplanRepository.findById(id).orElse(null);

        if (existplan != null) {
            // 이미 존재하는 여행 계획이 있으면, 타임라인만 업데이트
        	Travelplan travelplan = existplan;
            travelplan.setTimelines(timelines);  // 기존 타임라인 리스트를 새로운 리스트로 교체

            // 타임라인의 여행 계획 설정
            for (Timeline timeline : timelines) {
                timeline.setTravelplan(travelplan);  // 각 타임라인에 Travelplan 설정
            }

            return travelplanRepository.save(travelplan);  // 기존 여행 계획 업데이트
        } else {
            // 존재하지 않으면 새로운 TravelPlan 생성
            Travelplan travelplan = new Travelplan();
            travelplan.setId(id);  // userId를 TravelPlan의 id로 설정
            travelplan.setTimelines(timelines);  // 새로운 타임라인 리스트 설정

            // 타임라인의 여행 계획 설정
            for (Timeline timeline : timelines) {
                timeline.setTravelplan(travelplan);  // 각 타임라인에 TravelPlan 설정
            }

            return travelplanRepository.save(travelplan);  // 새로운 여행 계획 저장
        }
    }

    // 여행 계획 조회
	public List<Timeline> getTravelplanByUserId(String id) {
		return travelplanRepository.findById(id).orElse(null).getTimelines(); // 사용자 ID로 TravelPlan 조회
	}

    // 여행 계획 삭제(회원 탈퇴시)
    public void deleteTravelPlan(String id) {
        travelplanRepository.deleteById(id);  // 사용자 ID로 TravelPlan 삭제
    }

}
