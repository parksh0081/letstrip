package com.example.letstrip.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.letstrip.dto.MateCommentboardDTO;
import com.example.letstrip.entity.MateCommentboard;
import com.example.letstrip.service.MateCommentboardService;

@RestController
public class MateCommentboardRestController {
	
	@Autowired
	MateCommentboardService service;
	
    @PostMapping(value = "/mateboardComment")
    public ResponseEntity<MateCommentboard> mateboardComment(@RequestBody MateCommentboardDTO dto) {
    	dto.setLogtime(new Date());
    	MateCommentboard savedComment = service.MateCommentWrite(dto);
        return ResponseEntity.ok(savedComment);
    }

}


