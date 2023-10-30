<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="d-flex justify-content-center">
	<div class="sign-in-box col-9">
		<h1 class="my-4 font-weight-bold">로그인</h1>
		<div class="form-box d-flex justify-content-center align-items-center w-100">
			<form id="signInForm" method="post" action="user/signIn">
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="loginIdIcon">
							<img src="/static/img/userIcon.png" width="20">
						</span>
					</div>
					<input type="text" class="form-control" placeholder="Username">
				</div>
				<div class="input-group mb-3">
					<div class="input-group-prepend">
						<span class="input-group-text" id="passwordIcon">
							<img src="/static/img/passwordIcon.png" width="20">
						</span>
					</div>
					<input type="text" class="form-control" placeholder="password">
				</div>
				<div class="d-flex">
					<a href="/user/sign-up" class="btn btn-dark w-50">회원가입</a>
					<button type="submit" class="btn loginBtn btn-primary w-50 ml-2">로그인</button>
				</div>
			</form>
		</div>
	</div>
</div>