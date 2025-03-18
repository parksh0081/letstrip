package com.example.letstrip.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.MateCommentboardDAO;
import com.example.letstrip.dto.MateCommentboardDTO;
import com.example.letstrip.entity.MateCommentboard;

@Service
public class MateCommentboardService {
	
	@Autowired
	MateCommentboardDAO dao;
	
	public MateCommentboard MateCommentWrite(MateCommentboardDTO dto) {
		return dao.MateCommentWrite(dto);
	}

}
