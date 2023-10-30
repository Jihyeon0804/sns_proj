package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/user")
@RestController
public class UserRestController {

	// API 설계
	
	@PostMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedI(@RequestParam("loginId") String loginId) {
		
		// db 조회
		
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		result.put("code", 200);
		result.put("isDuplicated", true);
		return result;
		
	}
}
