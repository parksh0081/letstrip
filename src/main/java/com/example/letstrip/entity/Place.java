package com.example.letstrip.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Place {
	@Id
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
}
