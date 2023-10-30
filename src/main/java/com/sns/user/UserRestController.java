package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sns.common.EncryptUtils;
import com.sns.user.bo.UserBO;
import com.sns.user.entity.UserEntity;

@RequestMapping("/user")
@RestController
public class UserRestController {

	// API 설계
	
	@Autowired
	private UserBO userBO;
	
	@PostMapping("/is-duplicated-id")
	public Map<String, Object> isDuplicatedI(@RequestParam("loginId") String loginId) {
		
		// db 조회
		UserEntity user = userBO.getUserEntityByLoginId(loginId);
		
		Map<String, Object> result = new HashMap<>();
		
		// 응답값
		result.put("code", 200);
		
		if (user != null) { // 중복
			result.put("isDuplicated", true);
		} else {
			result.put("isDuplicated", false);
		}
		return result;
		
	}
	
	@PostMapping("/sign-up")
	public Map<String, Object> signUp(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, @RequestParam("name") String name
			, @RequestParam("email") String email) {
		
		String hashedPassword = EncryptUtils.md5(password);
		// db insert
		Integer id = userBO.addUserEntity(loginId, hashedPassword, name, email);
		
		// 응답값
		Map<String, Object> result = new HashMap<>();
		
		if (id == null) {
			result.put("code", 500);
			result.put("errorMessage", "회원가입 하는데 실패했습니다.");
		} else {
			result.put("code", 200);
		result.put("result", "성공");
		}
		
		return result;
		
	}
}
