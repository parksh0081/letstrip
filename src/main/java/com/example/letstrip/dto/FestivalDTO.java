package com.example.letstrip.dto;

import com.example.letstrip.entity.Festival;

import lombok.Data;

@Data
public class FestivalDTO {
   int seq;
    String name;
    int code;
    String area;
    String period;
    String month;
    String image1;
    String content;
    
    public Festival toEntity() {
       return new Festival(seq, name, code, area, period, month, image1, content);
    }
   

}
