package com.example.letstrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.letstrip.entity.Store;

public interface StoreRepository extends JpaRepository<Store, Integer>{
	
	// 찜 목록 
//	@Query(value="select store.*, place.* from store join place on store.placeid=place.id where store.id=:id order by seq desc", nativeQuery = true)
//	List<Object[]> findStoreById(@Param("id") String id);
	@Query(value="select * from store where id = :id order by seq desc", nativeQuery = true)
	List<Store> findStoreById(@Param("id") String id);
	
	// 확인 
	@Query(value="select * from store where id = :id and placeid=:placeid", nativeQuery = true)
	Store findByIdAndPlaceId(@Param("id") String id, @Param("placeid") String placeid);
	
	// 삭제 
	@Modifying
	@Query(value="delete from store where id = :id and placeid=:placeid", nativeQuery = true)
	int deleteByIdAndPlaceId(@Param("id") String id, @Param("placeid") String placeid);

}
