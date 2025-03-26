package com.example.letstrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.example.letstrip.entity.Reviewlike;

public interface ReviewlikeRepository extends JpaRepository<Reviewlike, Integer>{
	// 좋아요 확인
	@Query(value="select * from reviewlike where person_id=:person_id and review_seq=:review_seq", nativeQuery = true)
	Reviewlike findByIdAndSeq(@Param("person_id") String person_id, @Param("review_seq") int review_seq);
	
	// 사용자별 좋아요 리스트
	@Query(value="select * from reviewlike where person_id=:person_id", nativeQuery = true)
	List<Reviewlike>findAllbyId(@Param("person_id") String person_id);
	
	// 좋아요 취소 delete 
	@Modifying
	@Transactional
	@Query(value="delete from reviewlike where person_id=:person_id and review_seq=:review_seq",nativeQuery = true)
	int deleteBySeq(@Param("person_id") String person_id, @Param("review_seq") int review_seq);

}
