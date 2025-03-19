package com.example.letstrip.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.letstrip.dto.MateCommentboardDTO;
import com.example.letstrip.entity.MateCommentboard;
import com.example.letstrip.service.MateCommentboardService;

@RestController
public class MateCommentboardRestController {
	
	@Autowired
	MateCommentboardService service;
	
	// 댓글 추가
    @PostMapping(value = "/mateboardCommentWrite")
    public ResponseEntity<MateCommentboard> mateboardCommentWrite(@RequestBody MateCommentboardDTO dto) {
    	dto.setLogtime(new Date());
    	MateCommentboard savedComment = service.MateCommentWrite(dto);
        return ResponseEntity.ok(savedComment);
    }
    
    // 댓글 삭제
    @DeleteMapping(value = "/mateboardCommentDelete")
    public ResponseEntity<Boolean> mateboardCommentDelete(@RequestParam("commentseq") int commentseq) {
        boolean result = service.MateCommentDelete(commentseq);
        return ResponseEntity.ok(result);
    }
    
    // 댓글 리스트
    @GetMapping("/mateboardCommentList")
    public ResponseEntity<List<MateCommentboard>> mateboardCommentList(@RequestParam("mateboardseq") int mateboardseq) {
        List<MateCommentboard> commentList = service.mateCommentboardList(mateboardseq);
        return ResponseEntity.ok(commentList);
    }   
    	
}


