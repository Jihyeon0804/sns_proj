package com.sns.comment.bo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.comment.domain.Comment;
import com.sns.comment.mappper.CommentMapper;

@Service
public class CommentBO {

	@Autowired
	private CommentMapper commentMapper;
	
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
}
