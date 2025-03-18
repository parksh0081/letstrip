package com.example.letstrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.TravelplanDAO;
import com.example.letstrip.entity.Timeline;
import com.example.letstrip.entity.Travelplan;

@Service
public class TravelplanService {
	@Autowired
	TravelplanDAO dao;
	public Travelplan findTPById(String id) {
		return dao.findTPById(id);
	}
	
	public Travelplan createTravelPlan(String id) {
		return dao.createTravelPlan(id);
	}
	
}
