package com.sns.comment.domain;

import java.util.Date;

import lombok.Data;
import lombok.ToString;

@ToString
@Data	// getter, setter
public class Comment {
	
	// 필드
	private int id;
	private int postId;
	private int userId;
	private String content;
	private Date createdAt;
	private Date updatedAt;
}
