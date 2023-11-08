package com.sns.follow.bo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sns.follow.mapper.FollowMapper;

@Service
public class FollowBO {

	@Autowired
	private FollowMapper followMapper;
	
	public void followToggle(int followedId, int followingId) {
		
		if (followMapper.selectCountFollow(followedId, followingId) > 0) {
			// 팔로우 취소
			followMapper.deleteFollow(followedId, followingId);
		} else {
			// 팔로우
			followMapper.insertFollow(followedId, followingId);
		}
	}
	
	public boolean followStatus(int followedId, Integer followingId) {
		// 비로그인 상태
		if (followingId == null) {
			return false;
		}
		
		// 로그인 상태
		if (followMapper.selectCountFollow(followedId, followingId) > 0) {
			// 팔로우 중
			return true;
		} else {
			// 언팔로우 중
			return false;
		}
	}
}
