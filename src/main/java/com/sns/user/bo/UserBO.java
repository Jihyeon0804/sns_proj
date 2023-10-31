package com.sns.user.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.user.entity.UserEntity;
import com.sns.user.repository.UserRepository;

@Service
public class UserBO {
	
	@Autowired
	private UserRepository userRepository;
	
	// ---- 아이디 중복 확인 -----
	// input : loginId
	// output : UserEntity
	public UserEntity getUserEntityByLoginId(String loginId) {
		return userRepository.findByLoginId(loginId);
	}
	
	// ----- 회원가입 (insert) ------
	// input : params(login, password(암호화된), name, email)
	// output : pk
	public Integer addUserEntity(String loginId, String password, String name, String email) {
		UserEntity userEntity = userRepository.save(
				UserEntity.builder()
				.loginId(loginId)
				.password(password)
				.name(name)
				.email(email)
				.build());
		return userEntity == null ? null : userEntity.getId();
	}
	
	// ----- 아이디 & 비밀번호 조회 -----
	// input : loginId, password
	// output : UserEntity
	public UserEntity getUserEntityLoginIdPassword(String loginId, String password) {
		return userRepository.findByLoginIdAndPassword(loginId, password);
	}
}
