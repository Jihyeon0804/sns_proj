<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center mt-3">
	<div>
		<%-- 로그인 상태인 경우 글쓰기 영역 활성화 --%>
		<c:if test="${not empty userId}">
		<div class="contents-box p-2 mb-3">
			<textarea id="writeTextArea" class="w-100" placeholder="내용을 입력해주세요"></textarea>
			<div class="upload-box d-flex justify-content-between">
				<label for="imagePath">
					<img alt="파일업로드" src="/static/img/uploadIcon.png" width="35">
				</label>
				<input type="file" id="imagePath" class="d-none" onChange="imagePathFunc()" >
				<button type="submit" class="btn postBtn btn-info">게시</button>
			</div>
		</div>
		</c:if>
		
		<%-- 타임라인 영역 --%>
		<div id="timeline-box">
			<%-- 사용자 이름 --%>
			<div class="d-flex justify-content-between m-2">
				<span class="font-weight-bold ml-2">jeoooon22</span>
				<img alt="더보기" src="/static/img/more-icon.png" width="30">
			</div>
			<%-- 게시글 사진 --%>
			<div>
				<img src="/static/img/timeline-img.jpg" width="650">
			</div>
			<%-- 좋아요 --%>
			<div class="d-flex align-items-center m-2">
				<img src="/static/img/full-heart-icon.png" width="20">
				<span class="ml-2">좋아요</span>
				<span class="ml-2">11개</span>
			</div>
			<div class="my-3">
				<span class="font-weight-bold ml-2">jeoooon22</span>
				<span>글 내용</span>
			</div>
			<%-- 댓글 목록 --%>
			<div class="ml-2">
				<div class="comment">댓글</div>
				<div class="my-2">
					<div>
						<span>user1 :</span>
						<span>댓글 내용</span>
					</div>
					<div>
						<span>user2 :</span>
						<span>댓글 내용</span>
					</div>
				</div>
			</div>
			<%-- 댓글 입력 --%>
			<div class="input-group">
			  <input type="text" class="form-control comment-input" placeholder="댓글 달기">
			  <div class="input-group-append">
			    <button class="btn btn-light" type="button">게시</button>
			  </div>
			</div>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
	function imagePathFunc() {
		
	}
	// 게시물 업로드
	$('.postBtn').on('click', function() {
		let imagePath = $('#imagePath').val();
		let content = $('#writeTextArea').val();
		console.log(imagePath);
		console.log(content);
	});
});
</script>