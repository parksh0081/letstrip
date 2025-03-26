package com.example.letstrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.ThemaparkDAO;
import com.example.letstrip.entity.Themapark;

@Service
public class ThemaparkService {
	
	@Autowired
	ThemaparkDAO dao;
	
	// 목록 select
	public List<Themapark> selectList(){
		return dao.selectList();
	}

}
