package com.sns.comment.bo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.domain.CommentView;
import com.sns.comment.mappper.CommentMapper;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
	@Autowired
	private UserBO userBO;
	
	// input : postId, userId, comment
	// output : void
	public void addComment(int postId, int userId, String comment) {
		commentMapper.insertComment(postId, userId, comment);
	}
	
	
	// input : X
	// output : List<Comment>
	public List<Comment> getCommentList() {
		return commentMapper.selectCommentList();
	}
	
	// input : postId
	// output : List<CommentView>
	public List<CommentView> generateCommentViewListByPostId(int postId) {
		List<CommentView> commentViewList = new ArrayList<>();
		
		// 해당 게시글에 대한 댓글 가져오기 List<Comment>
		List<Comment> commentList = commentMapper.selectCommentListByPostId(postId);
		
		// 반복문 순회
		// List<Comment> => List<CommentView>
		for (Comment comment : commentList) {
			CommentView commentView = new CommentView();
			
			// 댓글 내용 담기
			commentView.setComment(comment);
			
			// 댓글 작성자 정보 담기(comment.userId)
			UserEntity user = userBO.getUserEntityById(comment.getUserId());
			commentView.setUser(user);
			
			// commentViewList에 담기
			commentViewList.add(commentView);
		}

		return commentViewList;
	}
	
	// input : 삭제할 댓글 번호
	// output : X
	public void deleteCommentById(int id) {
		commentMapper.deleteCommentById(id);
	}
}
