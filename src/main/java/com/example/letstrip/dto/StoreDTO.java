package com.example.letstrip.dto;

import com.example.letstrip.entity.Place;
import com.example.letstrip.entity.Store;

import lombok.Data;

@Data
public class StoreDTO {
    private int seq;
    private String id;
    private String placeid;
    private Place place;
    
    public Store toEntity() {
    	return new Store(seq, id, placeid, place);
    }
}
