package com.example.letstrip.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Blogboard {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
                    generator = "BLOGBOARD_SEQEUNCE_GENERATOR")
    @SequenceGenerator(name = "BLOGBOARD_SEQEUNCE_GENERATOR",
                        sequenceName = "seq_blogboard", initialValue = 1, allocationSize = 1)
    private int seq;                  // 글번호
    private String blogid;            // 블로그ID (person 테이블의 id와 연결됨)
    private String blogsubject;       // 블로그제목
    private String blogcontent;       // 블로그 내용
    private String blogimage1;        // 파일 이름 (업로드된 이미지 파일)
    private int hit;                  // 조회수
    @Temporal(TemporalType.DATE)
    private Date logtime;             // 작성일 (현재 시간으로 자동 설정)
}
