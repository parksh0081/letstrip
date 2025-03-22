package com.example.letstrip.repository;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.letstrip.entity.Review;

public interface MapReviewRepository extends JpaRepository<Review, Integer>{
	
	// 리뷰 목록 내림차순 
	@Query(value = "select * from review where placeid = :placeid order by seq desc", nativeQuery = true)
	public List<Review> findByPlaceid(@Param("placeid") String placeid);
	
	// 총 별점 평균 
	@Query(value="select ROUND(AVG(star), 1) from review where placeid=:placeid", nativeQuery = true)
	public Double findAverageStar(@Param("placeid") String placeid);
	
	// 각 별점 비율 
	@Query(value="select star, ROUND(count(star)*1.0/(select count (*) from review where placeid=:placeid), 2) as star_ratio from review  where placeid=:placeid group by star order by star desc", nativeQuery = true)
	public List<Map<String, Object>> findRatioStar(@Param("placeid") String placeid);
	
	// 총 리뷰 수 
	@Query(value = "select count(content) from review where placeid=:placeid", nativeQuery = true)
	public Integer findCountContent(@Param("placeid") String placeid);
	
}
