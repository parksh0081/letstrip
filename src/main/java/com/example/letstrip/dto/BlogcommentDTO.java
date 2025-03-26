package com.example.letstrip.dto;

import java.util.Date;

import com.example.letstrip.entity.Blogboard;
import com.example.letstrip.entity.Blogcomment;
import com.example.letstrip.entity.Person;

import lombok.Data;

@Data
public class BlogcommentDTO {

    private int commentseq;       // 댓글 ID
    private int blogseq;           // 블로그 글번호 (blogboard 테이블의 seq)
    private String commentwriter;  // 댓글 작성자 (작성자 ID)
    private String commentcontent; // 댓글 내용
    private Date commenttime;      // 댓글 작성 시간

    public Blogcomment toEntity(Blogboard blogboard, Person person) {
        return new Blogcomment(
            commentseq, 
            blogboard,  // Using the constructor that accepts the blogSeq (int)
            person,  	// Person 객체를 생성하여 연결
            commentcontent, 
            commenttime
        );
    }
    
    public BlogcommentDTO() {
        // 기본 생성자 구현
    }
    
    // Blogcomment 엔티티를 받아 DTO로 변환하는 생성자 추가
    public BlogcommentDTO(Blogcomment comment) {
        this.commentseq = comment.getCommentseq();
        this.blogseq = comment.getBlogboard().getSeq();   // Blogboard에서 seq 값 가져오기
        this.commentwriter = comment.getPerson().getId(); // Person에서 id 값 가져오기
        this.commentcontent = comment.getCommentcontent();
        this.commenttime = comment.getCommenttime();
    }
}

