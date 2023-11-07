package com.sns.timeline.domain;

import java.util.List;

import com.sns.comment.domain.CommentView;
import com.sns.post.entity.PostEntity;
import com.sns.user.entity.UserEntity;

import lombok.Data;

// View 용 객체
// CardView 1개는 글 1개와 매핑
@Data
public class CardView {
	// 글 1개
	private PostEntity post;
	
	// 글쓴이 정보
	private UserEntity user;
	
	// 해당 글과 매핑되는 댓글들
	private List<CommentView> commentList;
	
	// 좋아요 개수
	private int likeCount;
	
	// 내가 좋아요를 눌렀는지 여부
	private boolean filledLike;	// false - 빈 하트, true - 꽉 찬 하트
	
	// 내가 팔로우를 눌렀는지 여부
	private boolean followStatus;	// false - 팔로우 버튼, true - 팔로잉 버튼
}
