package com.example.letstrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.letstrip.entity.MateCommentboard;

public interface MateCommentboardRepository extends JpaRepository<MateCommentboard, Integer>{
	
	@Query(value = "select * from matecommentboard where mateboardseq=:mateboardseq order by commentseq", nativeQuery = true)
	List<MateCommentboard> findByCommentList(@Param("mateboardseq") int mateboardseq);	
	
    // comment_re_ref에 해당하는 가장 큰 comment_re_seq 값을 조회하는 쿼리
	@Query(value = "SELECT * FROM matecommentboard WHERE comment_re_ref = :comment_re_ref AND comment_re_lev = :comment_re_lev ORDER BY comment_re_seq DESC FETCH FIRST 1 ROWS ONLY", nativeQuery = true)
	MateCommentboard findMaxCommentByRefAndLev(@Param("comment_re_ref")int comment_re_ref, @Param("comment_re_lev")int comment_re_lev);
}
