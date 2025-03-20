package com.example.letstrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.letstrip.entity.MateCommentboard;

public interface MateCommentboardRepository extends JpaRepository<MateCommentboard, Integer>{
	
	@Query(value = "select * from matecommentboard " +
            "where mateboardseq = :mateboardseq " +
            "order by comment_re_ref, comment_re_seq", nativeQuery = true)
	List<MateCommentboard> findByCommentList(@Param("mateboardseq") int mateboardseq);

    // comment_re_ref과 comment_re_lev이 같은 값 중 가장 큰 comment_re_seq 값을 조회하는 쿼리
	@Query(value = "SELECT MAX(comment_re_seq) FROM matecommentboard WHERE comment_re_ref = :comment_re_ref AND comment_re_lev = :comment_re_lev", nativeQuery = true)
	Integer findMaxCommentByRefAndLev(@Param("comment_re_ref")int comment_re_ref, @Param("comment_re_lev")int comment_re_lev);
	
//	// 현재 선택한 댓글의 comment_re_seq 값을 조회하는 쿼리
//	@Query(value = "SELECT comment_re_seq FROM matecommentboard WHERE commentseq = :commentseq", nativeQuery = true)
//	Integer findSeqByRefAndLevAndSeq(@Param("commentseq") int commentseq);
	
	// 최대 ref
	@Query(value = "select max(comment_re_ref) from matecommentboard", nativeQuery = true)
	Integer lastRef();
	
	// 같은 ref중 comment_re_seq보다 크거나 같은 다른 comment_re_seq 값들에 + 1
	@Modifying(clearAutomatically = true)
	@Transactional	
	@Query(value = "update matecommentboard set comment_re_seq = comment_re_seq + 1 "
				 + "where comment_re_ref = :comment_re_ref and comment_re_seq > :comment_re_seq", nativeQuery = true)
	void updateSeq(@Param("comment_re_ref")int comment_re_ref, @Param("comment_re_lev")int comment_re_lev, @Param("comment_re_seq")int comment_re_seq);

	// 같은 ref 최대 seq 가져오기
	@Query(value = "select max(comment_re_seq) from matecommentboard where comment_re_ref = :comment_re_ref", nativeQuery = true)
	Integer lastSeq(@Param("comment_re_ref")int comment_re_ref);

}
