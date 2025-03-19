package com.example.letstrip.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    
    // 대댓글 최신
    @GetMapping("/getNextCommentReSeq")
    public ResponseEntity<Integer> getNextCommentReSeq(@RequestParam("comment_re_ref")int comment_re_ref, @RequestParam("comment_re_lev")int comment_re_lev) {
        try {
            // comment_re_ref에 해당하는 최신 comment_re_seq 값 가져오기
            int nextSeq = service.getNextCommentReSeq(comment_re_ref, comment_re_lev);
            return ResponseEntity.ok(nextSeq);  // 최신 seq 값을 클라이언트에 반환
        } catch (Exception e) {
            // 예외 처리
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }   
    	
}


