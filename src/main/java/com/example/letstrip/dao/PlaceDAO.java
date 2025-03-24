package com.example.letstrip.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.dto.PlaceDTO;
import com.example.letstrip.entity.Place;
import com.example.letstrip.repository.PlaceRepository;

@Repository
public class PlaceDAO {
	@Autowired
	PlaceRepository repository;
	
	public Place insert(PlaceDTO dto) {
		return repository.save(dto.toEntity());
	}
	
	public Place select(String place_id) {
		return repository.findById(place_id).orElse(null);
	}
}
