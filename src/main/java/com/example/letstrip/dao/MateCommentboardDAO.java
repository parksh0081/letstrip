package com.example.letstrip.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.letstrip.dto.MateCommentboardDTO;
import com.example.letstrip.entity.MateCommentboard;
import com.example.letstrip.repository.MateCommentboardRepository;

@Repository
public class MateCommentboardDAO {
	
	@Autowired
	MateCommentboardRepository mateCommentboardRepository;
	
	// 답글 저장
	@Transactional
	public MateCommentboard MateCommentWrite(MateCommentboardDTO dto) {
	    // 대댓글의 ref와 lev가 설정되어야 합니다.
	    int commentReRef = dto.getComment_re_ref();
	    int commentReLev = dto.getComment_re_lev();
	    int commentReSeq = dto.getComment_re_seq();
	    
	    // 1. 대댓글의 seq를 설정하기 전에, 기존 댓글들의 seq를 갱신합니다.
	    mateCommentboardRepository.updateSeq(commentReRef, commentReLev, commentReSeq);

	    // 2. 대댓글을 저장
	    MateCommentboard mateCommentboard = dto.toEntity();
	    MateCommentboard savedCommentboard = mateCommentboardRepository.save(mateCommentboard);

	    // 3. 대댓글 저장 후, 새로운 seq로 설정
	    return savedCommentboard;
	}
	
	// 답글 삭제
	public boolean MateCommentDelete(int commentseq) {
		// 1개 데이터
		boolean result = false;
		MateCommentboard mateCommentboard = mateCommentboardRepository.findById(commentseq).orElse(null);
		// 있으면 삭제
		if(mateCommentboard != null) {
			mateCommentboardRepository.delete(mateCommentboard);
			
			// 존재 검사
			if(!mateCommentboardRepository.existsById(commentseq)) {
				result = true;
			}
		} 
		return result;
	}
	
	// 댓글 목록
	public List<MateCommentboard> mateCommentboardList(int mateboardseq, String personId){
        // mateboardseq에 해당하는 댓글 목록 조회
        List<MateCommentboard> comments = mateCommentboardRepository.findByCommentList(mateboardseq);
        for (MateCommentboard comment : comments) {
			// 비밀 댓글인 경우, 로그인한 사용자가 작성자와 다르면 내용을 숨깁니다.
            if ("y".equals(comment.getIssecret()) && !comment.getCommentid().equals(personId)) {
                comment.setCommentcontent("비밀 댓글입니다.");  // 비밀 댓글 내용 숨김
            }
        }
        return comments;
    }	
	
	// 최신 댓글
	public int getNextCommentReSeq(int comment_re_ref, int comment_re_lev) {
	    Integer getcommentseq = mateCommentboardRepository.findMaxCommentByRefAndLev(comment_re_ref, comment_re_lev);
	    // 값이 없으면 0을 반환
	    if(getcommentseq == null) {
	    	getcommentseq = 0;
	    } 
	    return getcommentseq + 1; 
	}
	
	// 최대 ref
	public Integer lastRef() {
		return mateCommentboardRepository.lastRef();
	}
	
	// 동일한 ref, 최대 seq
	public Integer lastSeq(int comment_re_ref) {
		return mateCommentboardRepository.lastSeq(comment_re_ref);
	}


}
