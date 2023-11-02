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
	 * @param PostContent
	 * @param session
	 * @return
	 */
	@PostMapping("/create")
	public Map<String, Object> postCreate(
			@RequestParam("file") MultipartFile fileName
			, @RequestParam(value="content", required=false) String postContent
			, HttpSession session)  {
		
		int userId = (int)session.getAttribute("userId");
		String imagePath = "https://cdn.pixabay.com/photo/2023/10/28/19/54/chess-8348280_640.jpg";
		// db insert
		PostEntity postEntity = postBO.addPost(userId, postContent, imagePath);
		
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("postEntity", postEntity);
		result.put("code", 200);
		result.put("result", "성공");
		return result;
	}
	
}
