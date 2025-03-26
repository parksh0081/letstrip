package com.example.letstrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.letstrip.entity.Timeline;
import com.example.letstrip.entity.Travelplan;

public interface TravelplanRepository extends JpaRepository<Travelplan, String> {

	
}
