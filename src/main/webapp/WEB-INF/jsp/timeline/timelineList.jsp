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
				<button type="button" id="postBtn" class="btn btn-info">게시</button>
			</div>
		</div>
		</c:if>
		
		<%-- 타임라인 영역 --%>
		<c:forEach items="${postList}" var="post">
		<div id="timeline-box" class="mb-5">
			<%-- 사용자 이름 --%>
			<div class="d-flex justify-content-between m-2">
				<span class="font-weight-bold ml-2">${post.userId}</span>
				<img alt="더보기" src="/static/img/more-icon.png" width="30">
			</div>
			<%-- 게시글 사진 --%>
			<div>
				<img src="${post.imagePath}" width="650">
			</div>
			<%-- 좋아요 --%>
			<div class="d-flex align-items-center m-2">
				<img src="/static/img/full-heart-icon.png" width="20">
				<span class="ml-2">좋아요</span>
				<span class="ml-2">11개</span>
			</div>
			<div class="my-3">
				<span class="font-weight-bold ml-2">${post.userId}</span>
				<span>${post.content}</span>
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
		</c:forEach>
	</div>
</div>

<script>
$(document).ready(function() {
	// 게시물 업로드
	$('#postBtn').on('click', function() {
		let content = $('#writeTextArea').val();
		
		$.ajax({
			// request
			type:"post"
			, url:"/post/create"
			, data:{"content":content}
			
			// response
			, success:function(data) {
				if (data.code == 200) {
					// 새로고침
					location.reload();
				} else {
					// 로직 상 실패
					alert("업로드 실패")
				}
			}
			, error:function(request, status, error) {
				alert(data.errorMessage)
			}
		});
		
	});
});
</script>