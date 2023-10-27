package com.sns.test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sns.post.mapper.PostMapper;

@Controller
public class TestController {

	@Autowired
	private PostMapper postMapper;
	
	// 1. String + Response Body -> html
	@ResponseBody
	@GetMapping("/test1")
	public String test1() {
		return "Hello world";
	}
	
	
	// 2. Map + Response Body -> JSON
	@ResponseBody
	@GetMapping("/test2")
	public Map<String, Object> test2() {
		Map<String, Object> map = new HashMap<>();
		map.put("test", 1);
		map.put("테스트", "성공");
		map.put("a", "800");
		
		return map;
	}
	
	
	// 3. JSP -> html
	// build.gradle 두 줄 추가
	// 폴더 구조 생성
	// application.yml에 prefix, suffix 설정(mvc)
	@GetMapping("/test3")
	public String test3() {
		// src/main/webapp/WEB-INF/jsp/test/test.jsp
		return "test/test";
	}
	
	
	// 4. DB 연동 Response Body -> JSON
	// SnsApplication DB 설정 안보는 설정 제거
	// com.sns.config에 DatabaseConfig 클래스 추가
	// application.yml DB 접속 정보 추가(datasource) ; 서버 타임 존 지우기, test는 db명으로 변경
	// resources/mappers xml
	// logback-spring.xml 추가(쿼리 로그) ; logger name 속성 나의 base 패키지로 변경
	@ResponseBody
	@GetMapping("/test4")
	public List<Map<String, Object>> test4() {
		return postMapper.selectPostList();
	}
}
