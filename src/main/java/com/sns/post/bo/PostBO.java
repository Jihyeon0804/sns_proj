package com.sns.post.bo;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sns.comment.bo.CommentBO;
import com.sns.common.FileManagerService;
import com.sns.like.bo.LikeBO;
import com.sns.post.entity.PostEntity;
import com.sns.post.repository.PostRepository;

@Service
public class PostBO {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private CommentBO commentBO;
	
	@Autowired
	private LikeBO likeBO;
	
	@Autowired
	private FileManagerService fileManager;
	
	public List<PostEntity> getPostList() {
		return postRepository.findAllByOrderByIdDesc();
	}
	
	public PostEntity addPost(int userId, String content, MultipartFile file) {
		
		// /images/1_1698942335689/the-cat-8317334_640.jpg 형태
		String imagePath = fileManager.saveFile(userId, file);
		
		return postRepository.save(
				PostEntity.builder()
				.userId(userId)
				.content(content)
				.imagePath(imagePath)
				.build());
	}
	
	// 글 삭제
	@Transactional
	public void deletePostByIdAndUserId(int postId, int userId) {
		// 기존 글 => 이미지 삭제
		PostEntity post = postRepository.findByIdAndUserId(postId, userId);
		if (post == null) {
			return;
		}
		
		
		if (post.getImagePath() != null) {
			fileManager.deleteFile(post.getImagePath());
			
		}
		
		// db 글 삭제
		postRepository.delete(post);
		
		// db 댓글 삭제
		commentBO.deleteCommentByPostId(postId);
		
		// db 좋아요 삭제
		likeBO.deleteLikeByPostIdUserId(postId);
	}
}
