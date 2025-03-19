package com.example.letstrip.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.letstrip.dto.PlaceDTO;
import com.example.letstrip.entity.Place;
import com.example.letstrip.service.PlaceService;

@RestController
public class MapRestController {
	@Autowired
	PlaceService service;
	

	@PostMapping("/map/mapPlaceViewJson")
	public Map<String, Object> map(@RequestBody PlaceDTO dto, Model model){
		System.out.println(dto.toString());
		Place place=service.select(dto.getId());
		
		if (place==null){
			//TODO 사진 문제 해결...
			if(dto.getPlace_image()==null) {
				dto.setPlace_image("원조할아버지손두부.jpg");
			}
			place=service.insert(dto);
		}


		Map<String, Object> map = new HashMap<>();
		map.put("id", place.getId());
		map.put("place_name", place.getPlace_name());
		map.put("phone", place.getPhone());
		map.put("category_group_name", place.getCategory_group_name());
		map.put("road_address_name", place.getRoad_address_name());
		map.put("place_image", place.getPlace_image());
	
		
		return map;
	}
	
	
//	@GetMapping("/map/mapPlaceViewJson")
//	public Map<String, Object> map(@RequestParam(value="place_id", defaultValue="1") String place_id, Model model){
//		Place place=service.select(place_id);
//		if(place==null) {
//			
//		}
//
//		Map<String, Object> map = new HashMap<>();
//		map.put("place_id", place.getPlace_id());
//		map.put("place_name", place.getPlace_name());
//		map.put("place_phone", place.getPlace_phone());
//		map.put("place_addr", place.getPlace_addr());
//		map.put("place_road_addr", place.getPlace_road_addr());
//		map.put("place_image", place.getPlace_image());
//		
//		return map;
//	}
}
