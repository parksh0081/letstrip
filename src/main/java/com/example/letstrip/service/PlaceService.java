package com.example.letstrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.PlaceDAO;
import com.example.letstrip.dto.PlaceDTO;
import com.example.letstrip.entity.Place;

@Service
public class PlaceService {
	@Autowired
	PlaceDAO dao;
	
	public Place insert(PlaceDTO dto) {
		return dao.insert(dto);
	}
	
	public Place select(String place_id) {
		return dao.select(place_id);
	}
}
