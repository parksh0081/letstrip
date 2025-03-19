package com.example.letstrip.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "matecommentboard")
public class MateCommentboard {
    private int mateboardseq;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATECOMMENTBOARD_SEQUENCE_GENERATOR")
	@SequenceGenerator(name = "MATECOMMENTBOARD_SEQUENCE_GENERATOR", sequenceName = "seq_matecommentboard", initialValue = 1, allocationSize = 1)
    private int commentseq;            
    private String mateboardid; 
    private String commentid;
    private String commentcontent;
    private int comment_re_ref;
    private int comment_re_lev;
    private int comment_re_seq;
    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date logtime;
}
