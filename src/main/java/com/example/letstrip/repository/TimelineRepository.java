package com.example.letstrip.repository;

import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.letstrip.entity.Timeline;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {

	@Query(value="select * from timeline where plan_name=:plan_name and id=:id order by datetime asc", nativeQuery = true)
	List<Timeline> findByIdAndName(@Param("plan_name") String plan_name, @Param("id") String id);
	
	@Query(value="select plan_name from timeline where id = :id order by start_date asc", nativeQuery = true)
	Set<String> findListOrderByTime(@Param("id") String id);
	
	@Modifying
    @Transactional
	@Query(value="delete timeline where id=:id and plan_name=:plan_name", nativeQuery = true)
	void deleteList(@Param("id") String id, @Param("plan_name") String plan_name);
	
}
