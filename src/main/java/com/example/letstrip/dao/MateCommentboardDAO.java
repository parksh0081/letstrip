package com.example.letstrip.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.dto.MateCommentboardDTO;
import com.example.letstrip.entity.MateCommentboard;
import com.example.letstrip.entity.Mateboard;
import com.example.letstrip.repository.MateCommentboardRepository;

@Repository
public class MateCommentboardDAO {
	
	@Autowired
	MateCommentboardRepository mateCommentboardRepository;
	
	// 답글 저장
	public MateCommentboard MateCommentWrite(MateCommentboardDTO dto) {
		MateCommentboard mateCommentboard = dto.toEntity();
		dto.setComment_re_ref(dto.getCommentseq());
		return mateCommentboardRepository.save(mateCommentboard);
	}
	
	// 답글 삭제
	public boolean MateCommentDelete(int commentseq) {
		// 1개 데이터
		boolean result = false;
		MateCommentboard mateCommentboard = mateCommentboardRepository.findById(commentseq).orElse(null);
		// 있으면 삭제
		if(mateCommentboard != null) {
			mateCommentboardRepository.delete(mateCommentboard);
			
			// 존재 검사
			if(!mateCommentboardRepository.existsById(commentseq)) {
				result = true;
			}
		} 
		return result;
	}
	
	// 댓글 목록
	public List<MateCommentboard> mateCommentboardList(int mateboardseq){
		return mateCommentboardRepository.findByCommentList(mateboardseq);
	}	
	
	// 최신 댓글
	public int getNextCommentReSeq(int comment_re_ref, int comment_re_lev) {
	    MateCommentboard matecommentboard = mateCommentboardRepository.findMaxCommentByRefAndLev(comment_re_ref, comment_re_lev);
	    System.out.println(matecommentboard);
	    // 최대값이 없으면 1을 반환하고, 있으면 그 값에 1을 더해 반환
	    return (matecommentboard == null ? 1 : matecommentboard.getComment_re_seq() + 1);  // getCommentReSeq() 메서드를 통해 comment_re_seq 값에 접근
	}


}
