package com.sns.timeline;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.comment.bo.CommentBO;
import com.sns.comment.domain.Comment;
import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;

@RequestMapping("/timeline")
@Controller
public class TimelineController {

	@Autowired
	private PostBO postBO;
	
	@Autowired
	private CommentBO commentBO;
	
	@GetMapping("/list-view")
	public String timelistView(Model model) {
		
		List<PostEntity> postList = postBO.getPostList();
		
		List<Comment> commentList = commentBO.getCommentList();
		
		model.addAttribute("commentList", commentList);
		model.addAttribute("postList", postList);
		model.addAttribute("viewName", "/timeline/timelineList");
		return "template/layout";
	}
}
