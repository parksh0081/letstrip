package com.example.letstrip.dto;

import java.util.Date;

import com.example.letstrip.entity.Mateboard;

import lombok.Data;

@Data
public class MateboardDTO {
    private int seq;
    private String id;
    private String name;
    private String subject;
    private String content;
    private String matefile;
    private int good;
    private int hit;
    private Date logtime;
    
    public Mateboard toEntity() {
    	return new Mateboard(seq, id, name, subject, content, matefile, good, hit, logtime);
    }
}
