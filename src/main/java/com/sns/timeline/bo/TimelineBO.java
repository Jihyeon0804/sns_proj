package com.sns.timeline.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.CommentView;
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
	
	// input: X
	// output : List<CardView>
	public List<CardView> generateCardViewList() {
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
			
			// 내가 좋아요 눌렀는지 여부
			
			// ★★★★ 마지막에 CardViewList에 cardView를 넣는다. ★★★★
			cardViewList.add(cardview);
		}
		
		return cardViewList;
	}
}
