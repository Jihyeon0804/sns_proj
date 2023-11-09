package com.sns.post.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.sns.post.entity.PostEntity;

@Repository
public interface PostRepository extends JpaRepository<PostEntity, Integer>{

	public List<PostEntity> findAllByOrderByIdDesc();
	
	public PostEntity findByIdAndUserId(
			@Param("id") int postId,
			@Param("userId") int userId);
;	
	public void deletePostByIdAndUserId(
			@Param("id") int postId,
			@Param("userId") int userId);
}
