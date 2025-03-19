package com.example.letstrip.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letstrip.entity.Place;

public interface PlaceRepository extends JpaRepository<Place, String>{

}
