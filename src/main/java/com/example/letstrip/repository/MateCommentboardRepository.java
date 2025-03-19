package com.example.letstrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.letstrip.entity.MateCommentboard;

public interface MateCommentboardRepository extends JpaRepository<MateCommentboard, Integer>{
	
	@Query(value = "select * from matecommentboard where mateboardseq=:mateboardseq order by commentseq", nativeQuery = true)
	List<MateCommentboard> findByCommentList(@Param("mateboardseq") int mateboardseq);	
	
}
