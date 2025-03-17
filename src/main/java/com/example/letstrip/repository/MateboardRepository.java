package com.example.letstrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.letstrip.entity.Mateboard;

public interface MateboardRepository extends JpaRepository<Mateboard, Integer>{
	// 전체 목록
	@Query(value = "select * from "
			+ "(select rownum rn, tt.* from "
			+ "(select * from mateboard order by seq desc) tt) "
			+ "where rn>=:startNum and rn<=:endNum", nativeQuery = true)
	List<Mateboard> findByStartnumAndEndnum(@Param("startNum")int startNum, @Param("endNum")int endNum);
	
	// 검색 목록
	@Query(value = "select * from "
	        + "(select rownum rn, tt.* from "
	        + "(select * from mateboard where subject like %:search% order by seq desc) tt) "
	        + "where rn>=:startNum and rn<=:endNum", nativeQuery = true)    
	List<Mateboard> findByStartnumAndEndnumAndSearch(@Param("startNum") int startNum, @Param("endNum") int endNum, @Param("search") String search);
	
	// 검색 글 수
	@Query(value = "select count(*) as cnt from mateboard where subject like %:search%", nativeQuery = true)
	long countBySearch(@Param("search") String search);

}
