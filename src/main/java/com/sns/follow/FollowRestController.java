package com.sns.follow;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.follow.bo.FollowBO;

@RestController
public class FollowRestController {

	@Autowired
	private FollowBO followBO;
	
	@PostMapping("/follow")
	public Map<String, Object> follow(
			@RequestParam("followedId") int followedId,
			HttpSession session) {
		
		Map<String, Object> result = new HashMap<>();
		// 로그인 여부 조회
		Integer userId = (Integer)session.getAttribute("userId");
		
		if (userId == null) {
			result.put("code", 500);
			result.put("errorMessage", "로그인을 해주세요.");
			return result;
		}
		
		followBO.followToggle(followedId, userId);
		
		result.put("code", 200);
		result.put("result", "성공");
		return result;
		
	}
}
