package com.example.letstrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.StoreDAO;
import com.example.letstrip.dto.StoreDTO;
import com.example.letstrip.entity.Store;

import jakarta.transaction.Transactional;

@Service
public class StoreService {

	@Autowired
	StoreDAO dao;
	
	// 장소 찜에 저장 
	public Store insert(StoreDTO dto) {
		return dao.insert(dto);
	}
	
	// 찜 리스트 들고오기 
	public List<Store> selectList(String id){
		return dao.selectList(id);
	}
	
	// 찜 여부 확인 
	public Store selectCheck(String id, String placeid) {
		return dao.selectCheck(id, placeid);
	}
	
	// 찜 삭제
	@Transactional 
	public int delete(String id, String placeid) {
		return dao.delete(id, placeid);
	}
}
