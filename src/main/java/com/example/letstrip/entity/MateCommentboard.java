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

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class MateCommentboard {
    private int mateboardseq;
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MATEBOARD_SEQUENCE_GENERATOR")
	@SequenceGenerator(name = "MATEBOARD_SEQUENCE_GENERATOR", sequenceName = "seq_mateboard", initialValue = 1, allocationSize = 1)
    private int commentseq;            
    private String mateboardid; 
    private String commentid;
    private String commentcontent;
    private int comment_re_ref;
    private int comment_re_lev;
    private int comment_re_seq;
    @Temporal(TemporalType.DATE)
    private Date logtime;
}
