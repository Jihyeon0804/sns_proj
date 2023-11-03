package com.sns.comment;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.comment.bo.CommentBO;

@RequestMapping("/comment")
@RestController
public class CommentRestController {

	
	@Autowired
	private CommentBO commentBO;
	
	/**
	 * 댓글 쓰기
	 * @param postId
	 * @param comment
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> commentCreate(
			@RequestParam("postId") int postId,
			@RequestParam("comment") String comment,
			HttpSession session) {
		
		// 댓글 작성한 사람의 아이디
		int userId = (int)session.getAttribute("userId");
		
		// db insert
		commentBO.addComment(postId, userId, comment);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		return result;
	}

}
