<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@include file="visitor-header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request Received</title>

<style type="text/css">
.container {
	background: white;
	box-shadow: 15px 15px 20px;
	border-radius: 15px;
	box-shadow: 15px 15px 30px;
	margin-top: 80px;
	padding-top: 50px;
	padding-bottom: 50px;
}

.goBackIcon {
	font-size: 1.8rem;
}

.goBackIcon:hover {
	cursor: pointer;
}

body {
	margin-top: 120px;
}
</style>
</head>
<body>

	<div class="container col-lg-5" align="center">

		<div class="fs-3 fw-semibold text-decoration-underline">Congratulations!</div>

		<br />

		<div class="fs-5">Your request has been placed successfully.</div>

		<div class="fs-5">Please wait for our agent to contact you!</div>

		<br />

		<%-- 		<div class="col-lg-3">
			<a href="<%=request.getContextPath()%>/">
				<button type="button" class="btn btn-dark">Return Home</button>
			</a>
		</div> --%>

		<div class="goBackIcon">
			<a href="/InternetBanking/" class="text-decoration-none"> <i
				class="bi bi-arrow-left-circle">Go Back</i>
			</a>
		</div>

	</div>
</body>
</html>