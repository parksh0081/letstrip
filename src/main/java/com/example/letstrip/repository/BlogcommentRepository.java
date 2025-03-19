package com.example.letstrip.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.letstrip.entity.Blogcomment;

public interface BlogcommentRepository extends JpaRepository<Blogcomment, Integer>{
	// blogSeq로 댓글 조회하기
    List<Blogcomment> findAllByBlogboardSeq(int blogseq);  // 자동으로 쿼리 생성
}
