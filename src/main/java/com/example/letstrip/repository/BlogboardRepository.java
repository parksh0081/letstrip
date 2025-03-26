package com.example.letstrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.letstrip.entity.Blogboard;

//JpaRepository<Entity 클래스, PK 자료형>
public interface BlogboardRepository extends JpaRepository<Blogboard, Integer> {
	// 목록 조회 : 인덱스 뷰 사용
	@Query(value = "select * from "
			+ "(select rownum rn, tt.* from "
			+ "(select * from blogboard order by seq desc) tt) "
			+ "where rn >= :startNum and rn <= :endNum", nativeQuery = true)
	List<Blogboard> findByStartnumAndEndnum(@Param("startNum") int startNum,
										    @Param("endNum") int endNum);	
}
