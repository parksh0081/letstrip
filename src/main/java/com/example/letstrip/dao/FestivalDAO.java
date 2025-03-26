package com.example.letstrip.dao;

import com.example.letstrip.entity.Festival;
import com.example.letstrip.repository.FestivalRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class FestivalDAO {
	@Autowired
    FestivalRepository festivalRepository;

    public Page<Festival> findFestivalsByFilters(List<String> areas, List<String> months, String name, Pageable pageable) {
    	// 필터링 조건이 있을 경우 해당 조건으로 검색
        if (areas == null && months == null && name == null) {
            // 조건이 없으면 모든 데이터를 반환
            return festivalRepository.findAll(pageable);
        }

        // 조건에 맞는 페이징 데이터 반환
        return festivalRepository.findFestivalsByFilters(areas, months, name, pageable);
    }
}
