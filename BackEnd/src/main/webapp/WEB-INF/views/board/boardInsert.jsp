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

	<div>
		<br>
	</div>
	<div class="container">
			<form method="post" action="${root}/board/insert">
			<input type="hidden" name="boardId" value="${boardid}">
			<input type="hidden" name="writerId" value="${userInfo.userId}">
			<input type="text" style="width: 100%; margin-bottom: 10px" name="title" placeholder="제목을 입력해주세요">
			<textarea style="width: 100%; height: 600px;" name="content" placeholder="내용을 입력해주세요">
			</textarea>
			<button class="btn btn-primary">등록 완료</button>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>