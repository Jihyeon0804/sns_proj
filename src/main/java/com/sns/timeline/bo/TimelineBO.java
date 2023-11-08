package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
import com.sns.follow.bo.FollowBO;
import com.sns.like.bo.LikeBO;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;
import com.sns.timeline.domain.CardView;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class TimelineBO {
	
	@Autowired
	private PostBO postBO;
	
	@Autowired
	private UserBO userBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private FollowBO followBO;
	
	// input: userId(Integer => 비로그인/로그인 허용)
	// output : List<CardView>
	// 
	public List<CardView> generateCardViewList(Integer userId) {
		List<CardView> cardViewList = new ArrayList<>();	// []
		
		// 1) 글 목록 가져오기	List<PostEntity>
		List<PostEntity> postList = postBO.getPostList();
		
		
		// 2) List<PostEntity> 순회해서 cardViewList에 넣기 ; PostEntity => CardView => cardViewList
		// postEntity.userId = UserEntity.id
		for (PostEntity post : postList) {
			CardView cardview = new CardView();
			// 글 1개
			cardview.setPost(post);
			
			// 글쓴이 정보 세팅
			UserEntity user = userBO.getUserEntityById(post.getUserId());
			cardview.setUser(user);
			
			// 댓글들
			List<CommentView> commentList = commentBO.generateCommentViewListByPostId(post.getId());
			cardview.setCommentList(commentList);
			
			// 좋아요 카운트
			int countLike = likeBO.countLikeByPostId(post.getId());
			cardview.setLikeCount(countLike);
			
			// '내가' 좋아요 눌렀는지 여부
			// false : 비로그인 또는 누르지 않았을 경우
			boolean likeStatus = likeBO.likeStatus(post.getId(), userId);
			cardview.setFilledLike(likeStatus);
			
			// '내가' 팔로우를 했는지 여부
			boolean followStatus = followBO.followStatus(post.getUserId(), userId);
			cardview.setFollowStatus(followStatus);
			
			
			// ★★★★ 마지막에 CardViewList에 cardView를 넣는다. ★★★★
			cardViewList.add(cardview);
		}
		
		return cardViewList;
	}
}
