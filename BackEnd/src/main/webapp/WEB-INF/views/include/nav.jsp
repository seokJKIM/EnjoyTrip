<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<nav
	class="navbar navbar-expand-lg navbar-light bg-light shadow fixed-top">
	<div class="container">
		<a class="navbar-brand text-primary fw-bold" href="${root}/"> <img
			src="${root }/assets/img/trip_logo.png" alt="여행어때" width="100" />
		</a>
		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>
		<div class="collapse navbar-collapse" id="navbarSupportedContent">
			<ul class="navbar-nav me-auto mb-lg-0">
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="${root }/place/themetrip">테마 여행</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="${root }/place/myplan">나의 여행 계획</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="${root }/place/worldcup">관광지 월드컵</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="${root }/place/recommend">맛집 뽑기</a></li>
				<li class="nav-item"><a class="nav-link" aria-current="page"
					href="${root }/board/mvlist">게시판</a></li>
			</ul>
			<c:if test="${empty userInfo }">
				<ul class="navbar-nav mb-2 me-2 mb-lg-0" id="navbar-logout"
					style="display: flex; white-space: nowrap">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="#" data-bs-toggle="modal" data-bs-target="#registerModal">회원가입</a>
					</li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="#" data-bs-toggle="modal" data-bs-target="#loginModal">로그인</a>
					</li>
				</ul>
			</c:if>
			<c:if test="${!empty userInfo }">
				<ul class="navbar-nav mb-2 me-2 mb-lg-0" id="navbar-login"
					style="display: flex; white-space: nowrap">
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="${root}/user/logout" id="logout">로그아웃</a></li>
					<li class="nav-item"><a class="nav-link" aria-current="page"
						href="${root}/user/mypage">마이페이지</a></li>
				</ul>
			</c:if>
		</div>
	</div>
</nav>
<div style="height: 130px"></div>

<!-- 회원가입 Modal start -->
<div class="modal fade" id="registerModal" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">회원가입</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<div class="modal-body">
				<div class="input-form-backgroud row">
					<form class="input-form col-md-12 mx-auto" action="${root }/main">
						<div class="row">
							<input type="hidden" class="form-control" name="action"
								value="userregist" />
							<div class="col-md-6 mb-3">
								<label for="regid">아이디</label> <input type="text"
									class="form-control" id="regid" name="regid" placeholder=""
									value="" required />
								<div class="invalid-feedback">아이디를 입력해주세요.</div>
							</div>
							<div class="col-md-6 mb-3">
								<label for="nickname">닉네임</label> <input type="text"
									class="form-control" id="nickname" name="nickname"
									placeholder="" value="" required />
								<div class="invalid-feedback">닉네임을 입력해주세요.</div>
							</div>
						</div>
						<div class="mb-3">
							<label for="regpw">비밀번호</label> <input type="password"
								class="form-control" id="regpw" name="regpw" placeholder=""
								required />
							<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
						</div>

						<div class="mb-3">
							<label for="email">이메일</label> <input type="email"
								class="form-control" id="email" name="email"
								placeholder="you@example.com" required />
							<div class="invalid-feedback">이메일을 입력해주세요.</div>
						</div>
						<hr class="mb-4" />
						<div class="custom-control custom-checkbox">
							<input type="checkbox" class="custom-control-input"
								id="aggrement" required /> <label class="custom-control-label"
								for="aggrement">개인정보 수집 및 이용에 동의합니다.</label>
						</div>
						<div class="mb-4"></div>
						<input type="button" class="btn btn-primary btn-lg btn-block"
							id="register" value="가입 완료" onclick="joinEncrypt()">
					</form>
				</div>
			</div>
		</div>
	</div>
</div>
<!-- 회원가입 Modal end -->

<!-- 로그인 Modal start -->
<div class="modal fade" id="loginModal" data-bs-backdrop="static"
	data-bs-keyboard="false" tabindex="-1"
	aria-labelledby="staticBackdropLabel" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h5 class="modal-title">로그인</h5>
				<button type="button" class="btn-close" data-bs-dismiss="modal"
					aria-label="Close"></button>
			</div>
			<form action="${root }/user/login" method="post">
				<div class="modal-body">
					<div class="mb-3">
						<label for="userid">아이디</label> <input type="text"
							class="form-control" id="userid" name="userId" placeholder=""
							required />
						<div class="invalid-feedback">아이디를 입력해주세요.</div>
					</div>
					<div class="mb-3">
						<label for="userpw">비밀번호</label> <input type="password"
							class="form-control" id="userpass" name="userPass" placeholder=""
							required />
						<div class="invalid-feedback">비밀번호를 입력해주세요.</div>
					</div>
				</div>
				<div class="modal-footer flex">
					<!-- <button type="submit">로그인</button> -->
					<input type="button" class="btn btn-primary btn-lg btn-block" id="login" value="로그인" onclick="loginEncrypt()">
				</div>
			</form>
		</div>
	</div>
</div>
<!-- 로그인 Modal end -->
<script>
	let root = "${root}";
</script>

<script type="text/javascript" src="${root}/assets/js/core.min.js" /></script>
<script type="text/javascript" src="${root}/assets/js/sha256.min.js"></script>
<script src="${root }/assets/js/user.js"></script>

