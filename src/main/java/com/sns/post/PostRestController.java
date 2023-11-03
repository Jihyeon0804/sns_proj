package com.sns.post;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sns.post.bo.PostBO;
import com.sns.post.entity.PostEntity;

@RequestMapping("/post")
@RestController
public class PostRestController {
	
	@Autowired
	private PostBO postBO;
	
	/**
	 * 업로드 API
	 * @param file
	 * @param postContent
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> postCreate(
			@RequestParam("file") MultipartFile file
			, @RequestParam(value="content", required=false) String postContent
			, HttpSession session)  {
		
		// 로그인된 사용자의 아이디 저장
		int userId = (int)session.getAttribute("userId");
		
		// db insert
		PostEntity postEntity = postBO.addPost(userId, postContent, file);
		
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		if (postEntity != null) {
			result.put("postEntity", postEntity);
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "업로드에 실패했습니다.");
		}
		
		return result;
	}
	
}
