package com.example.letstrip.dao;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.letstrip.dto.ReviewDTO;
import com.example.letstrip.entity.Review;
import com.example.letstrip.repository.MapReviewRepository;

@Repository
public class MapReviewDAO {

	@Autowired
	MapReviewRepository repository;
	
	// 리뷰 insert 
	// 리뷰 저장시 리뷰이미지 테이블에 함께 저장됨 
	public Review insert(ReviewDTO dto) {
		return repository.save(dto.toEntity());
	}
	
	// 장소 id별로 리뷰 목록 select
	public List<Review> selectList(String placeid){
		return repository.findByPlaceid(placeid);
	}
	
	// 총 별점 평균 
	public double selectAvgStar(String placeid) {
		if(repository.findAverageStar(placeid)==null) {
			return 0.0;
		}
		return repository.findAverageStar(placeid);
	}
	
	// 각 별점 비율
	public List<Map<String, Object>> selectRatioStar(String placeid) {
		List<Map<String, Object>> list=repository.findRatioStar(placeid);
		
		// 1~5 별점 기본값을 0.0으로 초기화
        Map<Integer, Double> map = new HashMap<>();
        for (int i = 1; i <= 5; i++) {
            map.put(i, 0.0);
        }
        // DB 결과를 Map에 적용
        for (Map<String, Object> row : list) {
        	Integer star = ((Number) row.get("star")).intValue();  // BigDecimal → Integer 변환
            Double ratio = ((Number) row.get("star_ratio")).doubleValue();  // BigDecimal → Double 변환
            
            map.put(star, ratio);
        }

        // 변환된 데이터를 리스트로 반환
        List<Map<String, Object>> mapList = new ArrayList<>();
        for (int i = 5; i >= 1; i--) { // 5점부터 1점 순서대로 저장
            Map<String, Object> starMap = new HashMap<>();
            starMap.put("star", i);
            starMap.put("star_ratio",map.get(i));
            mapList.add(starMap);
        }
        
		return mapList;
	}
	
	// 총 리뷰 수 
	public int selectCountReview(String placeid) {
		if(repository.findCountContent(placeid)==null) {
			return 0;
		}
		return repository.findCountContent(placeid);
	}
	
	// 업데이트 좋아요 수 +
	public int updateReact(int seq) {
		return repository.updateReviewLike(seq);
	}
	
	// 업데이트 좋아요 수 -
	public int updateReactMinus(int seq) {
		return repository.updateReviewLikeCancel(seq);
	}
	
	// seq 로 리뷰 조회
	public Review selectReviewBySeq(int seq) {
		Review review=repository.findBySeq(seq);
		if(review!=null) {
			return review;
		}else {
			return null;
		}
	}
	
}
