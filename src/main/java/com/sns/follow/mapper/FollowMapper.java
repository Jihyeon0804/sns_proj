package com.sns.follow.mapper;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface FollowMapper {

	// 팔로우 존재 여부
	public int selectCountFollow(
			@Param("followedId") int followedId,
			@Param("followingId") int followingId);
	
	// 팔로우 취소
	public void deleteFollow(
			@Param("followedId") int followedId,
			@Param("followingId") int followingId);
	
	// 팔로우
	public void insertFollow(
			@Param("followedId") int followedId,
			@Param("followingId") int followingId);
}
