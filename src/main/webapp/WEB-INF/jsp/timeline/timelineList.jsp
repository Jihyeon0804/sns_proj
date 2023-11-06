<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center mt-3">
	<div>
		<%-- 로그인 상태인 경우 글쓰기 영역 활성화 --%>
		<c:if test="${not empty userId}">
		<div class="contents-box p-2 mb-3">
			<textarea id="writeTextArea" class="w-100" placeholder="내용을 입력해주세요"></textarea>
				<%-- file 태그를 숨겨두고 이미지르 클릭하면 file 태그를 클릭한 효과 --%>
				<%-- 이미지 업로드를 위한 아이콘과 업로드 버튼을 한 행에 멀리 떨어뜨리기 위한 div --%>
			<div class="d-flex justify-content-between">
				<div class="file-upload d-flex">
					<input type="file" id="file" accept=".jpg, .png, .jpeg, .gif" class="d-none">
					<a href="#" id="fileUploadBtn"><img width="35" src="/static/img/uploadIcon.png"></a>
					
					<%-- 업로드 된 임시 파일명 노출 --%>
					<div id="fileName" class="ml-2 d-flex align-items-center"></div>
				</div>
				<button id="postBtn" class="btn btn-info">게시</button>
			</div>
		</div>
		</c:if>
		
		<%-- 타임라인 영역 --%>
		<c:forEach items="${cardViewList}" var="card">
		<div id="timeline-box" class="mb-5">
			<%-- 사용자 이름 --%>
			<div class="d-flex justify-content-between align-items-center m-2">
				<span class="font-weight-bold ml-2">${card.user.loginId}</span>
				<img alt="더보기" src="/static/img/more-icon.png" width="30">
			</div>
			<%-- 게시글 사진 --%>
			<div>
				<img src="${card.post.imagePath}" width="650">
			</div>
			<%-- 좋아요 --%>
			<div class="d-flex align-items-center m-2">
				<img src="/static/img/full-heart-icon.png" width="20">
				<span class="ml-2">좋아요</span>
				<span class="ml-2">11개</span>
			</div>
			<div class="my-3">
				<span class="font-weight-bold ml-2">${card.user.loginId}</span>
				<span>${card.post.content}</span>
			</div>
			<%-- 댓글 목록 --%>
			<div class="ml-2">
				<div class="comment">댓글</div>
				<div class="my-2">
					<div>
						<span>${card.commentViewList.user.userId}</span>
						<span class="mx-2">${card.commentViewList.comment.content}</span>
						<a href="#" class="comment-del-btn">						
							<img alt="댓글 삭제" src="/static/img/x-icon.png" width="10">
						</a>
					</div>
				</div>
			</div>
			<%-- 댓글 입력 --%>
			<div id="commentBox" class="input-group">
			  <input type="text" class="form-control comment-input" placeholder="댓글 달기">
			  <div class="input-group-append">
			    <button class="comment-btn btn btn-light" type="button" data-post-id="${card.post.id}">게시</button>
			  </div>
			</div>
		</div>
		</c:forEach>
	</div>
</div>

<script>
$(document).ready(function() {
	// 파일 이미지 클릭 => 숨겨져 있던 type="file"을 동작시킨다.
	$('#fileUploadBtn').on('click', function(e) {
		e.preventDefault();
		$('#file').click();
	});
	
	
	// 이미지를 선택하는 순간 -> 유효성 확인 및 업로드 된 파일명 노출
	$('#file').on('change', function(e) {
		// 파일명 가져오기
		let fileName = e.target.files[0].name;		// chess-8348280_640.jpg
		console.log(fileName);
		
		// 확장자 유효성 확인
		let ext = fileName.split(".").pop().toLowerCase();
		
		if (ext != 'jpg' && ext != 'gif' && ext != 'png' && ext != 'jpeg') {
			alert("이미지 파일만 업로드 할 수 있습니다.");
			$('#file').val("");			// 파일 태그에 파일 제거(보이지 않지만 업로드 될 수 있으므로 주의)
			$('#fileName').text("");	// 파일명 비우기
			return;
		}
		
		// 유효성 통과한 이미지는 업로드 된 파일명 노출
		$('#fileName').text(fileName);
	});
	
	
	// 게시물 업로드
	$('#postBtn').on('click', function() {
		let fileName = $('#file').val();
		let content = $('#writeTextArea').val();
		console.log(fileName);
		
		if (fileName) {
			let ext = fileName.split(".").pop().toLowerCase();
			
			if (ext != 'jpg' && ext != 'gif' && ext != 'png' && ext != 'jpeg') {
				alert("이미지 파일만 업로드 할 수 있습니다.");
				$('#file').val("");			// 파일 태그에 파일 제거(보이지 않지만 업로드 될 수 있으므로 주의)
				$('#fileName').text("");	// 파일명 비우기
				return;
			}
			
		}
		
		let formData = new FormData();
		formData.append("file", $('#file')[0].files[0]);
		formData.append("content", content);
		
		$.ajax({
			// request
			type:"post"
			, url:"/post/create"
			, data:formData
			, enctype:"multipart/formdata"
			, processData:false
			, contentType:false
			
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
		
	});	// -- 게시물 업로드 끝
	
	// 댓글 쓰기
	$('.comment-btn').on('click', function() {
		let postId = $(this).data("post-id");
		let comment = $(this).closest('div').parent().find('input').val();
		console.log(postId)
		console.log(comment)
		if (!comment) {
			alert("댓글을 입력해주세요.");
			return;
		}
		
		// ajax
		$.ajax({
			//request
			type:"post"
			, url:"/comment/create"
			, data:{"postId":postId, "comment":comment}
			
			// response
			, success:function(data) {
				if(data.code == 200) {
					location.reload();
				} else if (data.code == 50) {
					alert(data.errorMessage);
					location.href="/user/sign-in-view"
				}
			}
			, error:function(request, status, error) {
				alert("댓글 쓰기 실패했습니다.")
			}
		});
		
	});
});
</script>