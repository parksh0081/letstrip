package com.example.letstrip.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.letstrip.dto.BlogcommentDTO;
import com.example.letstrip.entity.Blogboard;
import com.example.letstrip.entity.Blogcomment;
import com.example.letstrip.entity.Person;
import com.example.letstrip.service.BlogboardService;
import com.example.letstrip.service.BlogcommentService;
import com.example.letstrip.service.PersonService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/api/comments")
public class BlogcommentRestController {

    @Autowired
    private BlogcommentService blogcommentService;

    @Autowired
    private BlogboardService blogboardService;

    @Autowired
    private PersonService personService;


    // 댓글 목록 조회 (블로그 글 번호 기준)
    @GetMapping("/{blogseq}")
    public List<BlogcommentDTO> getCommentsByBlogSeq(@PathVariable("blogseq") int blogseq) {
        return blogcommentService.getCommentsByBlogSeq(blogseq);
    }

    // 댓글 저장
    @PostMapping("/{blogseq}")
    public ResponseEntity<BlogcommentDTO> saveComment(@PathVariable("blogseq") int blogseq, 
                                                      @RequestBody BlogcommentDTO dto, 
                                                      HttpServletRequest request) {
        HttpSession session = request.getSession();
        String id = (String) session.getAttribute("personId");

        if (id == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);  // 세션에 로그인 정보가 없을 경우
        }

        Person person = personService.getPerson(id);
        Blogboard blogboard = blogboardService.blogboardView(blogseq);

        // 댓글 저장 및 DTO 반환
        BlogcommentDTO savedDTO = blogcommentService.saveComment(dto, person, blogboard);

        // 로그로 확인: DTO 내용 출력
//        System.out.println("저장된 댓글 정보: ");
//        System.out.println("댓글 ID: " + savedDTO.getCommentseq());
//        System.out.println("블로그 글번호: " + savedDTO.getBlogseq());
//        System.out.println("댓글 작성자: " + savedDTO.getCommentwriter());
//        System.out.println("댓글 내용: " + savedDTO.getCommentcontent());
//        System.out.println("댓글 작성 시간: " + savedDTO.getCommenttime());
        
        // 저장된 댓글 DTO 반환
        return new ResponseEntity<>(savedDTO, HttpStatus.CREATED);  // 댓글 저장 후 응답 반환
    }
    
//    @PostMapping
//    public ResponseEntity<?> saveComment(@RequestBody BlogcommentDTO dto) {
//        Person person = personService.getPerson(dto.getCommentwriter());
//        Blogboard blogboard = blogboardService.blogboardView(dto.getBlogseq());
//
//        blogcommentService.saveComment(dto, person, blogboard);
//        return ResponseEntity.ok("댓글이 등록되었습니다.");
//    }

    
    @DeleteMapping("/{commentseq}")
    public ResponseEntity<Void> deleteComment(@PathVariable("commentseq") int commentseq) {
        boolean isDeleted = blogcommentService.deleteComment(commentseq);

        if (isDeleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // 삭제 성공
        } else {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);  // 삭제 실패
        }
    }
}

