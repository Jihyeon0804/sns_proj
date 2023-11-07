package com.sns.follow.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Follow {

	// userId가 팔로우하는 사용자의 id
	private int followedId;
	
	// 팔로우 하는 사용자의 id(현재 로그인 된 userId)
	private int followingId;
	
	private Date createdAt;
}
