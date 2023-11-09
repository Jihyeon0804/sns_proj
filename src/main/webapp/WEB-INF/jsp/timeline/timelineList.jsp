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
		<div id="timeline-box" class="my-5">
		
			<%-- 사용자 이름 --%>
			<div class="d-flex justify-content-between align-items-center m-2">
				<a href="#" class="username" data-user-id="${card.user.id}">
					<span class="font-weight-bold ml-2">${card.user.loginId}</span>
				</a>
				<div class="d-flex">
					<%-- 나를 제외한 사용자에게 팔로우 버튼 노출 --%>
					<c:if test="${card.user.id ne userId}">
						<c:if test="${card.followStatus eq false}">
						<a type="button" class="follow-btn btn-sm btn-primary" data-followed-id="${card.user.id}">
							<span class="m-2">팔로우</span>
						</a>
						</c:if>
						
						<c:if test="${card.followStatus eq true}">
						<a type="button" class="follow-btn btn-sm btn-secondary" data-followed-id="${card.user.id}">
							<span class="m-2">팔로잉</span>
						</a>
						</c:if>
					</c:if>
					
					<%-- 더보기 버튼 (내가 쓴 글일 대만 노출 - 삭제, 수정) --%>
					<c:if test="${card.user.id eq userId}">
					<a href="#" class="more-btn ml-2" data-toggle="modal" data-target="#modal" data-post-id="${card.post.id}">
						<img alt="더보기" src="/static/img/more-icon.png" width="30">
					</a>
					</c:if>
				</div>
			</div>
			
			<%-- 게시글 사진 --%>
			<div>
				<img src="${card.post.imagePath}" width="650">
			</div>
			
			<%-- 좋아요 --%>
			<div class="d-flex align-items-center m-2">
			<c:choose>
				<%-- 좋아요를 누르지 않았을 경우(false) ; 빈하트 : 1) 비로그인 2) 좋아요를 누르지 않았을 경우&&로그인된 상태 --%>
				<c:when test="${card.filledLike eq false}">
				<a href="#" class="like-btn" data-post-id="${card.post.id}">
					<img src="/static/img/heart-icon.png" width="20" alt="empty-heart">
				</a>
				</c:when>
				
				<%-- 좋아요를 눌렀을 경우(true) ; 꽉 찬 하트 : 좋아요를 누르지 않았을 경우&&로그인된 상태 --%>
				<c:otherwise>
				<a href="#" class="like-btn" data-post-id="${card.post.id}">
					<img src="/static/img/full-heart-icon.png" width="20" alt="full-heart">
				</a>
				</c:otherwise>
			</c:choose>
				<span class="ml-2">좋아요</span>
				
				<%-- 좋아요 개수 --%>
				<c:if test="${card.likeCount > 0}">
					<span class="ml-2">${card.likeCount}</span>
				</c:if>
			</div>
			<div class="my-3">
				<span class="font-weight-bold ml-2">${card.user.loginId}</span>
				<span>${card.post.content}</span>
			</div>
			
			<%-- 댓글 목록 --%>
			<div class="ml-2">
				<div class="comment">댓글</div>
				<div class="my-2">
					<c:forEach items="${card.commentList}" var="comment">
					<div class="card-comment">
						<span class="font-weight-bold">${comment.user.loginId}</span>
						<span class="mx-2">${comment.comment.content}</span>
						
						<%-- 내가(현재 로그인된 사용자) 쓴 댓글에만 삭제 버튼 생성 --%>
						<c:if test="${comment.user.id eq userId}">
						<a href="#" class="comment-del-btn" data-comment-id="${comment.comment.id}">						
							<img alt="댓글 삭제" src="/static/img/x-icon.png" width="10">
						</a>
						</c:if>
					</div>
					</c:forEach>
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



<!-- Modal 창 -->
<div class="modal fade" id="modal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
	<%-- 수직 기준 가운데 정렬(modal-dialog-centered), 작은 모달(modal-sm) --%>
	<div class="modal-dialog modal-dialog-centered modal-sm">
		<div class="modal-content text-center">
			<%-- 게시글 삭제 --%>
			<div class="py-3 border-bottom">
				<a href="#" id="deletePostLink">삭제하기</a>
			</div>
			
			<%-- 모달 창 닫기 --%>
			<div class="py-3">
				<a href="#" data-dismiss="modal">취소하기</a>
			</div>
		</div>
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
	
	// 댓글 삭제
	$('.comment-del-btn').on('click', function(e) {
		// alert("댓글 삭제 클릭");
		e.preventDefault();		// a 태그의 위로 올라가는 현상 방지
		
		let commentId = $(this).data("comment-id");
		// alert(commentId);
		
		// ajax
		$.ajax({
			// request
			type:"delete"
			, url:"/comment/delete"
			, data:{"commentId":commentId}
		
			// response
			, success:function(data) {
				if (data.code=200) {
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			}
			, error:function(request, status, error) {
				alert("댓글 삭제 하는데 실패했습니다.");
			}
		});
	});
	
	// 좋아요 버튼
	$('.like-btn').on('click', function(e) {
		// alert("like");
		e.preventDefault();
		
		let postId = $(this).data("post-id");
		
		$.ajax({
			// request
			url:"/like/"+postId
			// data X (postId 자체가 parameter로 넘어감)
			
			// response
			, success:function(data) {
				if (data.code == 200) {
					location.reload();
				} else if (data.code == 500) {
					alert(data.errorMessage);
				}
			}
			, error:function(request, status, error) {
				alert("다시 시도해 주세요");
			}
		});
	});
	
	// 팔로우 버튼
	$('.follow-btn').on('click', function(e) {
		e.preventDefault();
		// alert("follow");
		
		let followedId = $(this).data("followed-id");
		
		$.ajax({
			// request
			type:"post"
			, url:"/follow"
			, data:{"followedId":followedId}
			
			
			// response
			, success:function(data) {
				if (data.code == 200) {
					location.reload();
				} else if (data.code == 500) {
					alert(data.errorMessage);
					location.href="/user/sign-in-view";
				}
			}
			, error:function() {
				alert("잠시후 다시 시도해 주세요.");
			}
			
		});
	});
	
	// 글 삭제(... 더보기 버튼 클릭) => 모달 띄우기 => 모달에 글 번호 세팅
	$('.more-btn').on('click', function(e) {
		e.preventDefault();
		
		let postId = $(this).data("post-id");	// 더보기 버튼에 넣어둔 글 번호 getting
		// alert(postId);
		
		// 1개인 모달 태그에 재활용 (모달에 data-post-id에 심어두기)
		$('#modal').data("post-id", postId);	// 모달 태그에 setting
	});
	
	
	// 모달 창에 있는 삭제하기 클릭 => 진짜 삭제
	$('#modal #deletePostLink').on('click', function(e) {
		e.preventDefault();
		
		let postId = $('#modal').data("post-id");		// getting
		// alert(postId);
		
		
		// ajax
		$.ajax({
			// request
			type:"delete"
			, url:"/post/delete"
			, data:{"postId":postId}
			
			// response
			, success:function(data) {
				if (data.code == 200) {
					alert("해당 글이 삭제되었습니다.");
					location.reload();
				} else {
					alert(data.errorMessage);
				}
			}
			, error:function(request, status, error) {
				alert("해당 글을 삭제하는데 실패하였습니다.")
			}
		});
	});
});
</script>