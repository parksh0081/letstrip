package com.example.letstrip.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.entity.Themapark;
import com.example.letstrip.repository.ThemaparkRepository;

@Repository
public class ThemaparkDAO {
	
	@Autowired
	ThemaparkRepository repository;
	
	// 목록 
	public List<Themapark> selectList() {
		return repository.findAllOrderby();
		
	}
	

}
