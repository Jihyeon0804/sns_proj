package com.sns.timeline;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sns.timeline.bo.TimelineBO;
import com.sns.timeline.domain.CardView;

@RequestMapping("/timeline")
@Controller
public class TimelineController {

//	@Autowired
//	private PostBO postBO;
//	
//	@Autowired
//	private CommentBO commentBO;
	
	@Autowired
	private TimelineBO timelineBO;
	
	@GetMapping("/list-view")
	public String timelistView(Model model, HttpSession session) {
		// 좋지 않은 방식(게시글과 상관없는 댓글을 모두 가져오기 때문에 부하 발생)
//		게시글 가져오기
//		List<PostEntity> postList = postBO.getPostList();
//		댓글 가져오기
//		List<Comment> commentList = commentBO.getCommentList();
		
		
		// 게시글과 댓글이 담겨진 CardView를 뿌리는 방식
		Integer userId = (Integer)session.getAttribute("userId");
		List<CardView> cardViewList = timelineBO.generateCardViewList(userId);
		
//		model.addAttribute("commentList", commentList);
//		model.addAttribute("postList", postList);
		model.addAttribute("cardViewList", cardViewList);
		
		model.addAttribute("viewName", "/timeline/timelineList");
		return "template/layout";
	}
}
