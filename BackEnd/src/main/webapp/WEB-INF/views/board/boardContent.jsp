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

	<div class="container">
		<table class="table table-bordered" id="board"
			style="text-align: center;">
			<thead>
				<tr>
					<th>제목</th>
					<td colspan="5">${article.title}</td>
				</tr>
				<tr>
					<th>번호</th>
					<td>${article.num}</td>
					<th>작성자</th>
					<td>${article.writerId }</td>
					<th>조회수</th>
					<td>${article.hits }</td>
				</tr>
			</thead>
			<tbody>
				<tr style="height: 600px; text-align: left;">
					<td colspan="6">${article.content}</td>
				</tr>
			</tbody>
		</table>
		<c:if test="${userInfo.userId eq article.writerId}">
			<button type="button" id="btn-mv-modify"
				class="btn btn-outline-success mb-3 ms-1">글수정</button>
			<button type="button" id="btn-delete"
				class="btn btn-outline-danger mb-3 ms-1">글삭제</button>
			<form id="form-no-param" method="get" action="${root}/board">
				<input type="hidden" id="articleno" name="num"
					value="${article.num}">
			</form>
			<script>
				document.querySelector("#btn-mv-modify")
						.addEventListener(
								"click",
								function() {
									let form = document
											.querySelector("#form-no-param");
									form.setAttribute("action",
											"${root}/board/modify?num=${article.num}");
									form.submit();
								});

				document.querySelector("#btn-delete").addEventListener(
						"click",
						function() {
							if (confirm("정말 삭제하시겠습니까?")) {
								let form = document
										.querySelector("#form-no-param");
								form.setAttribute("action",
										"${root}/board/delete?num=${article.num}");
								form.submit();
							}
						});
			</script>
		</c:if>

	</div>
	<!-- 게시글 내용 -->

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>