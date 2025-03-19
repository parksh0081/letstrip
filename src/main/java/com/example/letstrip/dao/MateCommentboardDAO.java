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
		//System.out.println("Saving comment: " + dto);
		MateCommentboard mateCommentboard = dto.toEntity();
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

}
