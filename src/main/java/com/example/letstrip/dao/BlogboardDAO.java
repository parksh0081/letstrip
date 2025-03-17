package com.example.letstrip.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.dto.BlogboardDTO;
import com.example.letstrip.entity.Blogboard;
import com.example.letstrip.repository.BlogboardRepository;
@Repository
public class BlogboardDAO {
	@Autowired
	BlogboardRepository blogboardRepository;
	
	// 저장하기
	public Blogboard blogboardWrite(BlogboardDTO dto) {
		return blogboardRepository.save(dto.toEntity());
	}
	
	// 전체 데이터 개수 구하기
	public int getCount() {
		return (int) blogboardRepository.count();
	}
	
	// 인덱스뷰 목록보기
	public List<Blogboard> blogboardList(int startNum, int endNum){
		return blogboardRepository.findByStartnumAndEndnum(startNum, endNum);
	}
	
	// 상세보기
	public Blogboard blogboardView(int seq) {
		return blogboardRepository.findById(seq).orElse(null);
	}
	
	// 조회수 증가
	public Blogboard updateHit(int seq) {
		// 1. 기존 데이터 가져오기
		Blogboard blogboard = blogboardRepository.findById(seq).orElse(null);
		
		if(blogboard != null) {
			// 2. 수정 : 조회수 증가
			blogboard.setHit(blogboard.getHit() + 1);
			// 3. 저장
			return blogboardRepository.save(blogboard);
		}
		return null;
	}
	
	// 삭제하기
	public boolean blogboardDelete(int seq) {
		// 1. 기존 데이터 가져오기
		Blogboard blogboard = blogboardRepository.findById(seq).orElse(null);
		boolean result = false;
		
		if(blogboard != null) {
			// 2. 삭제 
			blogboardRepository.delete(blogboard); 
	        // 3. 존재 검사
			if(!blogboardRepository.existsById(seq)) {
				result = true;
	        }
		}
		return result;
	}	
	
	// 수정하기
	public boolean blogboardModify(BlogboardDTO dto) {
		// 1. 기존 데이터 가져오기
		Blogboard blogboard = blogboardRepository.findById(dto.getSeq()).orElse(null);
		boolean result = false;
		if(blogboard != null) { 	// 데이터가 존재하면
			// 2. 수정
			blogboard.setLogtime(new Date());				// 수정일 저장
			blogboard.setBlogsubject(dto.getBlogsubject());
			blogboard.setBlogcontent(dto.getBlogcontent());
			blogboard.setBlogimage1(dto.getBlogimage1());
			// 3. 저장
			Blogboard board_result = blogboardRepository.save(blogboard);
			if(board_result != null) {
				result = true;
			}
		}
		return result;
	}
}
