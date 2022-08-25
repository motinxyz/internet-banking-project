<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="admin-header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employee Details</title>

<style type="text/css">
.container {
	background: white;
	box-shadow: 15px 15px 20px;
	border-radius: 15px;
	box-shadow: 15px 15px 30px;
	margin-top: 80px;
	padding-top: 20px;
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

		<div class="goBackIcon" align="left">
			<a href="/InternetBanking/employees"> <i
				class="bi bi-arrow-left-circle"></i>
			</a>
		</div>



		<div class="col-lg-6 lh-lg" align=left>
			<div class="fs-4 fw-semibold text-decoration-underline"
				align="center">Employee Info:</div>
			<br />

			<div class="fs-6">
				<span class="fw-bold">Name: &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; </span> ${userInfo.name }
			</div>

			<div class="fs-6">
				<span class="fw-bold">Address: &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp; &nbsp; &nbsp;</span>${userInfo.address }
			</div>

			<div class="fs-6">
				<span class="fw-bold">Phone Number:&nbsp; &nbsp;</span>
				${userInfo.phone_number }
			</div>

			<div class="fs-6">
				<span class="fw-bold">Email:</span> &nbsp; &nbsp; &nbsp; &nbsp;
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ${userInfo.email }
			</div>


			<div class="fs-6">
				<span class="fw-bold">Employee: &nbsp; &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp; &nbsp;</span> ${userInfo.permission_type }
			</div>

			<div class="fs-6">
				<span class="fw-bold">Deactivated: &nbsp; &nbsp; &nbsp;
					&nbsp; &nbsp;</span> ${userInfo.frozen }
			</div>

		</div>


		<div class="d-flex justify-content-center mt-4" align="center">

			<c:if test="${userInfo.frozen == 'no'}">

				<br />
				<a class="btn btn-danger me-2"
					href="/InternetBanking/un-freeze-employee/${userInfo.user_id}/freeze"
					onclick="if(!(confirm('Do you want to DEACTIVATE the account of ${userInfo.name}, User ID: ${userInfo.user_id}?'))) return false">Deactivate</a>


			</c:if>

			<c:if test="${userInfo.permission_type == 'teller'}">

				<br />
				<a class="btn btn-danger"
					href="/InternetBanking/remove-employee/${userInfo.user_id}"
					onclick="if(!(confirm('Do you want to REMOVE the account of ${userInfo.name}, User ID: ${userInfo.user_id}?'))) return false">Remove</a>


			</c:if>

		</div>

	</div>

</body>
</html>