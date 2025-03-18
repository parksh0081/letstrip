package com.example.letstrip.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.dto.MateCommentboardDTO;
import com.example.letstrip.entity.MateCommentboard;
import com.example.letstrip.repository.MateCommentboardRepository;

@Repository
public class MateCommentboardDAO {
	
	@Autowired
	MateCommentboardRepository mateCommentboardRepository;
	
	// 답글 저장
	public MateCommentboard MateCommentWrite(MateCommentboardDTO dto) {
		System.out.println("Saving comment: " + dto);
		MateCommentboard mateCommentboard = dto.toEntity();
		return mateCommentboardRepository.save(mateCommentboard);
	}

}
