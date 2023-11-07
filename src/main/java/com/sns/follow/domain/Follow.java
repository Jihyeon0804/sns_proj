package com.sns.follow.domain;

import lombok.Data;

@Data
public class Follow {

	private int id;
	
	private int following;
	
	private int followed;
	
	// 팔로우 상태
	// false => 팔로우 버튼 활성화
	// true => 팔로잉 버튼 활성화
	private boolean followStatus;
}
