package com.sns.comment;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sns.comment.bo.CommentBO;

@RequestMapping("/comment")
@Controller
public class CommentController {

	@Autowired
	private CommentBO commentBO;
	
	// 댓글 삭제
	@RequestMapping("/delete")
	public String deleteComment(@RequestParam("commentId") int id) {
		commentBO.deleteCommentById(id);
		return "redirect:/timeline/list-view";
	}
}
