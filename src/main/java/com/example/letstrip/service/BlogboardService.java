package com.example.letstrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.BlogboardDAO;
import com.example.letstrip.dto.BlogboardDTO;
import com.example.letstrip.entity.Blogboard;

@Service
public class BlogboardService {
	@Autowired
	BlogboardDAO dao;

	// 저장하기
	public Blogboard blogboardWrite(BlogboardDTO dto) {
		return dao.blogboardWrite(dto);
	}

	// 전체 데이터 개수 구하기
	public int getCount() {
		return dao.getCount();
	}

	// 인덱스뷰 목록보기
	public List<Blogboard> blogboardList(int startNum, int endNum) {
		return dao.blogboardList(startNum, endNum);
	}

	// 상세보기
	public Blogboard blogboardView(int seq) {
		return dao.blogboardView(seq);
	}
	
	// 조회수 증가
	public Blogboard updateHit(int seq) {
		return dao.updateHit(seq);
	}
	
	// 삭제하기
	public boolean blogboardDelete(int seq) {
		return dao.blogboardDelete(seq);
	}
	
	// 수정하기
	public boolean blogboardModify(BlogboardDTO dto) {
		return dao.blogboardModify(dto);
	}
}
