package com.sns.like.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.like.mapper.LikeMapper;

@Service
public class LikeBO {

	@Autowired
	private LikeMapper likeMapper;
	
	public void likeToggle(int postId, int userId) {
		// 셀렉트 => count(*)
		if (likeMapper.selectLikeCountByPostIdUserId(postId, userId) > 0) {
			// 삭제
			likeMapper.deleteLikeByPostIdUserId(postId, userId);
		} else {
			// 추가
			likeMapper.insertLike(postId, userId);
		}
	}
	
	public int countLikeByPostId(int postId) {
		return likeMapper.selectLikeCountByPostId(postId);
	}
	
	public boolean likeStatus(int postId, int userId) {
		if (likeMapper.selectLikeCountByPostIdUserId(postId, userId) > 0) {
			return true;
		} else {
			return false;
		}
	}
}