<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="ibAdmin-header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request Response</title>

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
</style>


</head>
<body>

	<div class="container col-lg-6" align="center">


		<div class="col-lg-6">
			<div class="fs-3 fw-semibold text-decoration-underline">REQUEST
				STATUS</div>
			<br />

			<div class="fs-6">
				<span class="fw-bold">Account Number: </span> ${user.account_number }
			</div>

			<div class="fs-6">
				<span class="fw-bold">Name: </span> ${user.name }
			</div>


			<div class="fs-6">
				<span class="fw-bold">Address: </span>${user.address }
			</div>

			<div class="fs-6">
				<span class="fw-bold">Phone Number: </span> ${user.phone_number }
			</div>

			<br />


			<div class="fs-5">
				<span class="fw-bold">Status: </span>

				<c:if test="${requestStatus == 'Rejected'}">
					<span class="text-danger">${requestStatus }</span>
				</c:if>

				<c:if test="${requestStatus == 'Accepted'}">
					<span class="text-success">${requestStatus }</span>
				</c:if>

			</div>


		</div>

		<br />

		<div class="goBackIcon">
			<a href="/InternetBanking/pending-ibanking-requests"
				class="text-decoration-none"> <i class="bi bi-arrow-left-circle">Go
					Back</i>
			</a>
		</div>

	</div>
</body>
</html>