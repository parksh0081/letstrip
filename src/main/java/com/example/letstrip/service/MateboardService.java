package com.example.letstrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.MateboardDAO;
import com.example.letstrip.entity.Mateboard;

@Service
public class MateboardService {
	
	@Autowired
	MateboardDAO dao;
	
	// 게시판 목록
	public List<Mateboard> mateboardList(int startNum, int endNum){
		return dao.mateboardList(startNum, endNum);
	}
	
	// 전체 개수 구하기
	public int totalMateA() {
		return dao.totalMateA();
	}
	
	// 게시판 검색 목록
	public List<Mateboard> mateboardListSearch(int startNum, int endNum, String search){
		return dao.mateboardListSearch(startNum, endNum, search);
	}	

}
