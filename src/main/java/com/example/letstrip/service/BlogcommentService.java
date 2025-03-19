package com.example.letstrip.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.letstrip.dao.BlogcommentDAO;
import com.example.letstrip.dto.BlogcommentDTO;
import com.example.letstrip.entity.Blogboard;
import com.example.letstrip.entity.Blogcomment;
import com.example.letstrip.entity.Person;

@Service
public class BlogcommentService {

    @Autowired
    BlogcommentDAO dao;

    // 댓글 저장하기
    public BlogcommentDTO saveComment(BlogcommentDTO dto, Person person, Blogboard blogboard) {
        return dao.saveComment(dto, person, blogboard);
    }

    // 특정 블로그의 댓글 목록 가져오기
    public List<BlogcommentDTO> getCommentsByBlogSeq(int blogseq) {
        return dao.getCommentsByBlogSeq(blogseq);
    }

    // 댓글 삭제하기
    public boolean deleteComment(int commentseq) {
        return dao.deleteComment(commentseq);
    }
}
