<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="ibAdmin-header.jsp"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>${name }</title>

<style type="text/css">
body {
	padding-top: 25px;
}

.container {
	background: white;
	box-shadow: 15px 15px 20px;
	border-radius: 15px;
	box-shadow: 15px 15px 30px;
	/* 	margin-top: 80px; */
	padding-top: 30px;
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

		<div class="fs-6 text-end mb-3 fst-italic">
			<a class="link-dark text-decoration-none" href="#"> <i
				class="bi bi-pencil-square"></i> Update Info
			</a>
		</div>

		<div class="fs-3 fw-semibold text-decoration-underline">Personal
			Info</div>

		<div class="col-lg-8 fs-6 lh-lg" align="left">

			<br />

			<div>
				<span class="fw-bold">Account Number: </span> &nbsp;${account_number }
			</div>

			<div>
				<span class="fw-bold">Name: </span> &nbsp; &nbsp; &nbsp; &nbsp;
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;${name }
			</div>

			<div>
				<span class="fw-bold">Father's Name: </span> &nbsp; &nbsp; &nbsp;
				${father_name }
			</div>

			<div>
				<span class="fw-bold">Mother's Name: </span> &nbsp; &nbsp;
				&nbsp;${mother_name }
			</div>

			<div>
				<span class="fw-bold">Date of Birth: </span> &nbsp; &nbsp; &nbsp;
				&nbsp; &nbsp;${date_of_birth }
			</div>

			<div>
				<span class="fw-bold">Address: </span>&nbsp; &nbsp; &nbsp; &nbsp;
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;${address }
			</div>

			<%-- 			<div>
				<span class="fw-bold">Phone Number: </span> &nbsp; &nbsp; &nbsp;
				${phone_number }
			</div>

			<div>
				<span class="fw-bold">Email: </span> &nbsp; &nbsp; &nbsp; &nbsp;
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ${email }
			</div> --%>


		</div>

		<br />

		<div class="goBackIcon">
			<a href="/InternetBanking/" class="text-decoration-none"> <i
				class="bi bi-arrow-left-circle">Go Back</i>
			</a>
		</div>

	</div>
</body>