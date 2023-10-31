<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="sign-in-box col-9">
		<h1 class="my-4 font-weight-bold">로그인</h1>
		<div class="form-box d-flex justify-content-center align-items-center w-100">
			<form id="signInForm" method="post" action="/user/sign-in">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="loginIdIcon">
							<img src="/static/img/userIcon.png" width="20">
						</span>
					</div>
					<input type="text" id="loginId" name="loginId" class="form-control" placeholder="Username">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="passwordIcon">
							<img src="/static/img/passwordIcon.png" width="20">
						</span>
					</div>
					<input type="password" id="password" name="password" class="form-control" placeholder="password">
				</div>
				<div class="d-flex">
					<a href="/user/sign-up-view" class="btn btn-dark w-50">회원가입</a>
					<button type="submit" class="btn loginBtn btn-primary w-50 ml-2">로그인</button>
				</div>
			</form>
		</div>
	</div>
</div>

<script>
$(document).ready(function() {
	
	// submit
	$('#signInForm').on('submit', function(e) {
		e.preventDefault();
		
		let loginId = $('#loginId').val().trim();
		let password = $('#password').val();
		
		if (!loginId) {
			alert("아이디를 입력해주세요.");
			return false;
		}
		
		if (!password) {
			alert("비밀번호를 입력해주세요.");
			return false;
		}
		
		// AJAX
		let url = $(this).attr('action');
		let params = $(this).serialize();
		// console.log(url);
		// console.log(params);
		$.post(url, params)
		.done(function(data) {
			if (data.code == 200) {
				// 성공 => 타임라인 화면으로 이동
				location.href="/timeline/list-view";
			} else {
				// 로직 상 실패
				alert(data.errorMessage)
			}
		});
	});
});
</script>