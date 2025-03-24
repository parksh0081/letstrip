package com.example.letstrip.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.dto.StoreDTO;
import com.example.letstrip.entity.Store;
import com.example.letstrip.repository.StoreRepository;

@Repository
public class StoreDAO {
	
	@Autowired
	StoreRepository repository;
	
	// 장소 찜에 저장 
	public Store insert(StoreDTO dto) {
		return repository.save(dto.toEntity());
	}
	
	// 찜 리스트 들고오기 
	public List<Store> selectList(String id){
		return repository.findStoreById(id);
	}
	
	// 찜 여부 확인 
	public Store selectCheck(String id, String placeid) {
		Store store=repository.findByIdAndPlaceId(id, placeid);
		if(store!=null) {
			return store;
		}else {
			return null;
		}
	}
		
	// 찜 삭제 
	public int delete(String id, String placeid) {
		return repository.deleteByIdAndPlaceId(id, placeid);
	}
}
