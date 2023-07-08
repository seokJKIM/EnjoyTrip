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

	<!-- start -->
	<div class="row justify-content-center">
		<div class="col-12 ">
			<h2 style="text-align: center;" id="title">국내 관광지 월드컵 8강 (1/4)</h2>
		</div>
		<div class="row justify-content-center">
			<div
				class="col-4 border border-primary rounded-3 bg-primary-subtle p-3"
				style="text-align: center;">
				<img src="${root }/assets/img/worldcup7.png" alt=""
					class="img-fluid rounded-3" id="img1" />
				<h2 style="text-align: center;" id="text1">밤바다 국룰 여수</h2>
			</div>
			<div class="col-1 align-self-center">
				<h2 style="text-align: center;">VS</h2>
			</div>
			<div
				class="col-4 border border-primary rounded-3 bg-primary-subtle p-3"
				style="text-align: center;">
				<img src="${root }/assets/img/worldcup2.png" alt=""
					class="img-fluid rounded-3" id="img2" />
				<h2 style="text-align: center;" id="text2">돈있으면 당연히 제주도</h2>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	
	</script>
	<!-- end -->

	<%@ include file="/WEB-INF/views/include/footer.jsp"%>