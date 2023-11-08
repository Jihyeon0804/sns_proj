package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	
	// input : postId, userId
	// output : X
	public void likeToggle(int postId, int userId) {
		// 셀렉트 => count(*)
		if (likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0) {
			// 삭제
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else {
			// 추가
			likeMapper.insertLike(postId, userId);
		}
	}
	
	
	// input : postId
	// output : int(Count)
	public int countLikeByPostId(int postId) {
		return likeMapper.selectLikeCountByPostIdOrUserId(postId, null);
	}
	
	
	// input : postId, userId(Integer : 로그인 여부 판단)
	// output : boolean(조건문에 해당하는 데이터가 있으면 true, 없으면 false)
	public boolean likeStatus(int postId, Integer userId) {
		// 비로그인 (db조회 필요X)
		if (userId == null) {
			return false;
		}
		
		// 로그인 (db조회)
		if (likeMapper.selectLikeCountByPostIdOrUserId(postId, userId) > 0) {
			// 꽉 찬 하트
			return true;
		} else {
			// 빈 하트
			return false;
		}
	}
}