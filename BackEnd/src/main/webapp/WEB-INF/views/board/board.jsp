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
	<div class="container text-center">
		<!-- 그리드 -->
		<div class="row">
			<!-- 게시판 리스트 목록 start -->
			<div class="col-4">
				<div class="list-group" id="list-tab" role="tablist">
					<a class="list-group-item list-group-item-action active"
						id="board1-list" data-bs-toggle="list" href="#list-board1"
						role="tab" aria-controls="list-board1" onclick="callBoard(1)">공지사항</a>
					<a class="list-group-item list-group-item-action" id="board2-list"
						data-bs-toggle="list" href="#list-board2" role="tab"
						aria-controls="list-board2" onclick="callBoard(2)">여행 후기</a> 
					<a class="list-group-item list-group-item-action" id="board3-list"
						data-bs-toggle="list" href="#list-board3" role="tab"
						aria-controls="list-board3" onclick="callBoard(3)">자유게시판</a>
					<a class="list-group-item list-group-item-action" id="board4-list"
						data-bs-toggle="list" href="#list-board4" role="tab"
						aria-controls="list-board4" onclick="callBoard(4)">일행 구하기</a>
				</div>
			</div>
			<!-- 게시판 리스트 목록 end -->

			<!-- 게시판 내용 start -->
			<div class="col-8">
				<div class="tab-content" id="nav-tabContent">
					<div class="tab-pane fade show active" id="list-board1"
						role="tabpanel" aria-labelledby="board1-list">
						<h3>공지사항</h3>
						<table class="table table-hover" id="board">
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody id = "noticeBoard">
							</tbody>
						</table>
						<!-- 검색창 -->
						<form method="post" action="${root}/board/write">
							<input type="hidden" name="boardid" value="1">
							<button class="btn btn-primary" style="float: right;">글쓰기</button>
						</form>
						<div>
							<select id = "noticeCondition">
								<option value="0">제목</option>
								<option value="1">내용</option>
								<option value="2">제목+내용</option>
							</select> <input type="text" name="trip-search" id="noticeKeyword">
							<button type="button" class="btn btn-outline-primary" onclick="searchBoard(1)">검색</button>
						</div>
					</div>
					<div class="tab-pane fade" id="list-board2" role="tabpanel"
						aria-labelledby="board2-list">
						<h3>여행 후기</h3>
						<table class="table table-hover" id="board">
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody id = "reviewBoard">
							</tbody>
						</table>
						<!-- 검색창 -->
						<form method="post" action="${root}/board/write">
							<input type="hidden" name="boardid" value="2">
							<button class="btn btn-primary" style="float: right;">글쓰기</button>
						</form>
						<div>
							<select id = "reviewCondition">
								<option value="0">제목</option>
								<option value="1">내용</option>
								<option value="2">제목+내용</option>
							</select> <input type="text" name="trip-search" id="reviewKeyword">
							<button type="button" class="btn btn-outline-primary" onclick="searchBoard(2)">검색</button>
						</div>
					</div>
					<div class="tab-pane fade" id="list-board3" role="tabpanel"
						aria-labelledby="board3-list">
						<h3>자유게시판</h3>
						<table class="table table-hover" id="board">
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody id = "freeBoard">
							</tbody>
						</table>
						<!-- 검색창 -->
						<form method="post" action="${root}/board/write">
							<input type="hidden" name="boardid" value="3">
							<button class="btn btn-primary" style="float: right;">글쓰기</button>
						</form>
						<div>
							<select id = "freeCondition">
								<option value="0">제목</option>
								<option value="1">내용</option>
								<option value="2">제목+내용</option>
							</select> <input type="text" name="trip-search" id="freeKeyword">
							<button type="button" class="btn btn-outline-primary" onclick="searchBoard(3)">검색</button>
						</div>
					</div>
					<div class="tab-pane fade" id="list-board4" role="tabpanel"
						aria-labelledby="board4-list">
						<h3>일행 구하기</h3>
						<table class="table table-hover" id="board">
							<thead>
								<tr>
									<th>번호</th>
									<th>제목</th>
									<th>작성자</th>
									<th>조회수</th>
								</tr>
							</thead>
							<tbody id = "teamBoard">
							</tbody>
						</table>
						<!-- 검색창 -->
						<form method="post" action="${root}/board/write">
							<input type="hidden" name="boardid" value="4">
							<button class="btn btn-primary" style="float: right;">글쓰기</button>
						</form>
						<div>
							<select id = "teamCondition">
								<option value="0">제목</option>
								<option value="1">내용</option>
								<option value="2">제목+내용</option>
							</select> <input type="text" name="trip-search" id="teamKeyword">
							<button type="button" class="btn btn-outline-primary" onclick="searchBoard(4)">검색</button>
						</div>
					</div>
				</div>
			</div>
			<!-- 게시판 내용 end -->

		</div>
	</div>
	<!-- Main end -->
	<script src="${root}/assets/js/board.js"></script>
	
	<%@ include file="/WEB-INF/views/include/footer.jsp"%>