package com.sns.comment.domain;

import com.sns.user.entity.UserEntity;

import lombok.Data;

// 한 개의 댓글
@Data
public class CommentView {
	
	// 댓글 작성자 정보
	private UserEntity user;
	
	// 댓글 내용
	private Comment comment;
}
