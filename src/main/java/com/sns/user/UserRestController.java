package com.sns.user;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;

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
	
	/**
	 * 아이디 중복 확인 API
	 * @param loginId
	 * @return
	 */
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
	
	
	/**
	 * 회원가입 API
	 * @param loginId
	 * @param password
	 * @param name
	 * @param email
	 * @return
	 */
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
	
	/**
	 * 로그인 API
	 * @param loginId
	 * @param password
	 * @param session
	 * @return
	 */
	@PostMapping("/sign-in")
	public Map<String, Object> signIn(
			@RequestParam("loginId") String loginId
			, @RequestParam("password") String password
			, HttpSession session) {
		
		String hashedPassword = EncryptUtils.md5(password);
		
		// db 조회
		UserEntity user = userBO.getUserEntityLoginIdPassword(loginId, hashedPassword);
		
		Map<String, Object> result = new HashMap<>();
		
		// 응답값
		if (user != null) {
			// 로그인 처리
			session.setAttribute("userId", user.getId());
			session.setAttribute("userLoginId", user.getLoginId());
			session.setAttribute("userName", user.getName());
			session.setAttribute("userEmail", user.getEmail());
			
			result.put("code", 200);
			result.put("result", "성공");
		} else {
			result.put("code", 500);
			result.put("errorMessage", "아이디 또는 비밀번호가 올바르지 않습니다.");
		}
		return result;
	}
}
