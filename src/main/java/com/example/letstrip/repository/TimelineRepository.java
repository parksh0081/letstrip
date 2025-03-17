package com.example.letstrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.letstrip.entity.Timeline;

public interface TimelineRepository extends JpaRepository<Timeline, Long> {

	@Query(value="select * from timeline where id = :id order by datetime asc", nativeQuery = true)
	List<Timeline> findAllOrderByTime(@Param("id") String id);
	
}
