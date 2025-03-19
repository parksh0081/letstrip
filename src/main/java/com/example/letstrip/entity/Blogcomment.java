package com.example.letstrip.entity;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
public class Blogcomment {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COMMENT_SEQUENCE_GEERATOR")
	@SequenceGenerator(name = "COMMENT_SEQUENCE_GEERATOR", sequenceName = "comment_seq", initialValue = 1, allocationSize = 1)
	private int commentseq;              // 댓글 ID 
	
	@ManyToOne
	@JoinColumn(name = "blogseq", nullable = false)	 //블로그 글번호 (blogboard 테이블의 seq)
	private Blogboard blogboard;		// 게시글과의 관계 onetomany 관계의 Many 측)
	
	@ManyToOne
	@JoinColumn(name = "commentwriter", referencedColumnName = "id", nullable = false) // 댓글 작성자 
	private Person person;
	 
    private String commentcontent;      // 댓글 내용
    
    @Temporal(TemporalType.TIMESTAMP)
    private Date commenttime;            // 댓글 작성 시간 (자동으로 현재 시간 설정)
    
    
    @PrePersist
    public void prePersist() {
    	if (commenttime == null) {
    		commenttime = new Date(); 		// 댓글 작성 시간 자동 설정
    	}
    }
}
