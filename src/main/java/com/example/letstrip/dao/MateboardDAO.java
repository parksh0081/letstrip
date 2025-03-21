package com.example.letstrip.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.example.letstrip.dto.MateboardDTO;
import com.example.letstrip.entity.Mateboard;
import com.example.letstrip.repository.MateboardRepository;

@Repository
public class MateboardDAO {
	
	@Autowired
	MateboardRepository mateboardRepository;
	
	// 게시판 목록
	public List<Mateboard> mateboardList(int startNum, int endNum){
		return mateboardRepository.findByStartnumAndEndnum(startNum, endNum);
	}
	
	// 전체 글 수 구하기
	public int totalMateA() {
		return (int) mateboardRepository.count();
	}
	
	// 게시판 검색 목록
	public List<Mateboard> mateboardListSearch(int startNum, int endNum, String search){
		return mateboardRepository.findByStartnumAndEndnumAndSearch(startNum, endNum, search);
	}
	
	// 검색 글 수 구하기
	public int totalMateSearchA(String search) {
		return (int) mateboardRepository.countBySearch(search);
	}
	
	// 게시판 글 쓰기
	public Mateboard mateboardWrite(MateboardDTO dto) {
		Mateboard mateboard = dto.toEntity();
		return mateboardRepository.save(mateboard);
	}
	
	// 게시판 상세보기
	public Mateboard mateboardView(int seq) {
		return mateboardRepository.findById(seq).orElse(null);
	}
	
	// 게시판 삭제하기
	public boolean mateboardDelete(int seq) {
		Mateboard mateboard = mateboardRepository.findById(seq).orElse(null);
		boolean result = false;
		if(mateboard != null) {
			mateboardRepository.delete(mateboard);
			if(!mateboardRepository.existsById(seq)) {
				result = true;
			}
		}
		return result;
	}
	
	// 게시판 수정하기
	public boolean mateboardModify(MateboardDTO dto) {
		boolean result = false;
		
		// 새로 저장
		Mateboard newMateboard = dto.toEntity();
		mateboardRepository.save(newMateboard);
		
		if(newMateboard != null) {
			result = true;
		}
		return result;
	}
	

}