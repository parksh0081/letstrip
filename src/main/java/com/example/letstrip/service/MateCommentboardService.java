package com.example.letstrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.MateCommentboardDAO;
import com.example.letstrip.dto.MateCommentboardDTO;
import com.example.letstrip.entity.MateCommentboard;

@Service
public class MateCommentboardService {
	
	@Autowired
	MateCommentboardDAO dao;
	
	// 댓글 쓰기
	public MateCommentboard MateCommentWrite(MateCommentboardDTO dto) {
		return dao.MateCommentWrite(dto);
	}
	
	// 댓글 삭제
	public boolean MateCommentDelete(int commentseq) {
		return dao.MateCommentDelete(commentseq);
	}	
	
	// 댓글 목록
	public List<MateCommentboard> mateCommentboardList(int mateboardseq, String personId){
		return dao.mateCommentboardList(mateboardseq, personId);
	}
	
	// 최신 댓글
	public int getNextCommentReSeq(int comment_re_ref, int comment_re_lev) {
		return dao.getNextCommentReSeq(comment_re_ref, comment_re_lev);
	}
	
	// 최대 ref
	public Integer lastRef() {
		return dao.lastRef();
	}

	// 동일한 ref, 최대 seq
	public Integer lastSeq(int comment_re_ref) {
		return dao.lastSeq(comment_re_ref);
	}	
	
}
