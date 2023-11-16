<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"   uri="http://java.sun.com/jsp/jstl/core" %>
<div class="d-flex justify-content-center mt-3">
	<div>
		<header class="d-flex feed-header bg-white">
			<%-- 사용자 프로필 이미지 --%>
			<div class="user-img-box d-flex justify-content-center">
				<img alt="님의 이미지" src="/static/img/user-profile-icon.png" width="150">
			</div>
			
			<%-- 상세 정보 --%>
			<section class="user-info">
				<div class="d-flex">
					<div>user.loginId</div>
					<a type="button" class="follow-btn btn-sm btn-primary" data-followed-id="id">
						<span class="m-2">팔로우</span>
					</a>
				</div>
				<div class="d-flex">
					<div>게시물 개수</div>
					<div>팔로워 수</div>
					<div>팔로잉 수</div>
				</div>
				<div>
					<div>user.Name</div>
				</div>
			</section>
		</header>
		
		<%-- 상세 버튼 --%>
		<div class="d-flex justify-content-center my-2 border-top">
			<button class="btn">게시물</button>
			<button class="btn">릴스</button>
			<button class="btn">태그됨</button>
		</div>
		
		<%-- 게시물 --%>
		<div>
			<div class="feed">
				<div class="feed-box"><img src="/static/img/no-Image-100.png" width="260"></div>
				<div class="feed-box"><img src="/static/img/no-Image-100.png" width="260"></div>
				<div class="feed-box"><img src="/static/img/no-Image-100.png" width="260"></div>
			</div>
			<div class="feed">
				<div class="feed-box"><img src="/static/img/no-Image-100.png" width="260"></div>
				<div class="feed-box"><img src="/static/img/no-Image-100.png" width="260"></div>
				<div class="feed-box"><img src="/static/img/no-Image-100.png" width="260"></div>
			</div>
		</div>
	</div>
</div>