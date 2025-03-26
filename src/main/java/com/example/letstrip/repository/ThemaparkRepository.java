package com.example.letstrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.letstrip.entity.Themapark;

public interface ThemaparkRepository extends JpaRepository<Themapark, Integer>{

	@Query(value="select * from themapark order by thema_name", nativeQuery = true)
	public List<Themapark> findAllOrderby();
}
