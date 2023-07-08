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
			<form method="post" action="${root}/board/modify">
			<input type="hidden" name="num" value="${article.num}">
			<input type="text" style="width: 100%; margin-bottom: 10px" name="title" value="${article.title}">
			<textarea style="width: 100%; height: 600px;" name="content">
				${article.content}
			</textarea>
			<button class="btn btn-primary">수정 완료</button>
		</form>
	</div>
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>