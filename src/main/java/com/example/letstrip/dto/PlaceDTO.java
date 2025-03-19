package com.example.letstrip.dto;

import com.example.letstrip.entity.Place;

import lombok.Data;

@Data
public class PlaceDTO {
    private String id;
    private String place_name;
    private String phone;
    private String address_name;
    private String road_address_name;
    private String category_group_name;
    private String category_group_code;
    private String category_name;
    private String distance;
    private String place_url;
    private String x;
    private String y;
    private String place_image;
    
    public Place toEntity() {
    	return new Place(id, place_name, phone, address_name, road_address_name, category_group_name,
    			category_group_code, category_name, distance, place_url, x, category_group_code, place_image);
    }
}
