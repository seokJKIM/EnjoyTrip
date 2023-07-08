<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">

<head>
<%@ include file="/WEB-INF/views/include/head.jsp"%>
</head>

<body>
	<!-- 상단 navbar start -->
	<%@ include file="/WEB-INF/views/include/nav.jsp"%>
	<!-- 상단 navbar end -->

	<!-- Main start -->
	<div class="container">
		<ul class="nav nav-tabs">
			<li class="nav-item"><a class="nav-link tap active"
				data-bs-toggle="tab" data-bs-target="#userInfo"
				onclick="taplist(this)" aria-current="page" href="#">내 프로필</a></li>
			<li class="nav-item"><a class="nav-link tap"
				data-bs-toggle="tab" data-bs-target="#userPost"
				onclick="taplist(this)" href="#">작성한 글 보기</a></li>
			<li class="nav-item"><a class="nav-link tap"
				data-bs-toggle="tab" data-bs-target="#changeData"
				onclick="taplist(this)" href="#">프로필 정보 수정</a></li>
		</ul>
		<div class="tab-content" id="myTabContent">
			<div class="tab-pane fade show active" id="userInfo" role="tabpanel"
				aria-labelledby="userInfo-tab">
				<table class="table">
					<tbody>
						<tr>
							<th scope="row">아이디</th>
							<td colspan="2" id="userid">${userInfo.userId}</td>
						</tr>
						<tr>
							<th scope="row">닉네임</th>
							<td colspan="2" id="nickname">${userInfo.userName}</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="tab-pane fade" id="userPost" role="tabpanel"
				aria-labelledby="userPost-tab">
				<div class="col-9" id="table-box">
					<table class="table table-hover text-center" >
						<thead>
							<tr>
								<th class="text-center">번호</th>
								<th class="text-center">제목</th>
								<th class="text-center">작성자</th>
								<th class="text-center">조회수</th>
							</tr>
						</thead>
						<tbody id="mypageBoard">
						</tbody>
					</table>
				</div>
			</div>
			<div class="tab-pane fade" id="changeData" role="tabpanel"
				aria-labelledby="changeData-tab">

				<div class="col-lg-4">
					<form method="post" action="${root}/modify">
						<input type="hidden" id="hiddenId" value="${userInfo.userId}">
						<div class="row">
							<div class="col-lg-12">
								<label for="originalpw">기존 비밀번호</label> <input type="text"
									class="form-control" id="originalpw" name="originalpw"
									placeholder="" value="" required />
							</div>
						</div>
						<div class="row">
							<div class="col-lg-6">
								<label for="changpw">새 비밀번호</label> <input type="text"
									class="form-control" id="changepw" name="changepw"
									placeholder="" value="" required />
							</div>
							<div class="col-lg-6">
								<label for="changpw2">새 비밀번호 확인</label> <input type="text"
									class="form-control" id="changepw2" name="changepw2"
									placeholder="" value="" required />
							</div>
						</div>

						<div class="col-lg-5 col-md">
							<input type="button" class="btn btn-primary btn-lg btn-block m-1"
								id="changePW" value="비밀번호 변경" onclick="changePass()">
						</div>
					</form>
					<form method="get" action="${root}/user/delete">
						<div class="col-lg-5 col-md">
							<button class="btn btn-primary btn-lg btn-danger btn-block m-1"
								id="login">회원 탈퇴</button>
						</div>
					</form>
				</div>

			</div>
		</div>
	</div>
	<!-- Main end -->
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>
	<script type="text/javascript">
		let id = "${userInfo.userId}"
	</script>
	<script src="${root}/assets/js/mypageBoard.js"></script>
	<script type="text/javascript" src="${root}/assets/js/mypage.js" /></script>