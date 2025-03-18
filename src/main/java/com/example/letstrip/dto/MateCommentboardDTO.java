package com.example.letstrip.dto;

import java.util.Date;

import com.example.letstrip.entity.MateCommentboard;

import lombok.Data;

@Data
public class MateCommentboardDTO {
    private int mateboardseq;
    private int commentseq;            
    private String mateboardid; 
    private String commentid;
    private String commentcontent;
    private int comment_re_ref;
    private int comment_re_lev;
    private int comment_re_seq;
    private Date logtime;
    
    public MateCommentboard toEntity() {
    	return new MateCommentboard(mateboardseq, commentseq, mateboardid, commentid, commentcontent, comment_re_ref, comment_re_lev, comment_re_seq, logtime);
    }
}
