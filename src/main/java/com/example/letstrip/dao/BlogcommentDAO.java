package com.example.letstrip.dao;

import com.example.letstrip.dto.BlogcommentDTO;
import com.example.letstrip.entity.Blogcomment;
import com.example.letstrip.entity.Blogboard;
import com.example.letstrip.entity.Person;
import com.example.letstrip.repository.BlogcommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BlogcommentDAO {

    @Autowired
    private BlogcommentRepository blogcommentRepository;

    
//    public List<Blogcomment> getCommentsByBlogSeq(int blogseq) {
//        return blogcommentRepository.findAllByBlogboardSeq(blogseq);
//    }
    
    
    // 댓글 목록 조회
    public List<BlogcommentDTO> getCommentsByBlogSeq(int blogseq) {
        List<Blogcomment> comments = blogcommentRepository.findAllByBlogboardSeq(blogseq);
        
        // 댓글 리스트를 DTO 리스트로 변환
        return comments.stream()
                .map(BlogcommentDTO::new) // Blogcomment 엔티티를 DTO로 변환
                .collect(Collectors.toList());
    }
    
    // 댓글 저장
    public BlogcommentDTO saveComment(BlogcommentDTO dto, Person person, Blogboard blogboard) {
        // DTO에서 엔티티 변환 후 저장
        //return blogcommentRepository.save(dto.toEntity(blogboard, person));
    	Blogcomment comment = dto.toEntity(blogboard, person);
        
        // 저장된 댓글을 DTO로 변환하여 반환
        Blogcomment savedComment = blogcommentRepository.save(comment);
        return new BlogcommentDTO(savedComment); 
    }
    
    // 댓글 삭제
    public boolean deleteComment(int commentseq) {
        try {
            blogcommentRepository.deleteById(commentseq);
            return true;
        } catch (Exception e) {
            // 예외 처리 (삭제 실패 시 처리할 로직 추가 가능)
            e.printStackTrace();
            return false;
        }
    }
}
