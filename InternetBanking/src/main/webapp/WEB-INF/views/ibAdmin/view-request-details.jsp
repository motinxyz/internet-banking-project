<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="ibAdmin-header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Request Details</title>

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
			<a href="/InternetBanking/pending-ibanking-requests"> <i
				class="bi bi-arrow-left-circle"></i>
			</a>
		</div>

		<div class="row" style="padding-top: 20px;">


			<div class="col-lg-6" align="left">
				<div class="fs-4 fw-semibold text-decoration-underline">Received
					Info:</div>
				<br />

				<div class="fs-6">
					<span class="fw-bold">Name: </span> ${receivedInfo.name }
				</div>


				<div class="fs-6">
					<span class="fw-bold">Father's Name: </span>
					${receivedInfo.father_name }
				</div>

				<div class="fs-6">
					<span class="fw-bold">Mother's Name: </span>
					${receivedInfo.mother_name }
				</div>

				<div class="fs-6">
					<span class="fw-bold">Address: </span>${receivedInfo.address }
				</div>

			</div>


			<div class="vr" style="width: 5px;"></div>



			<div class="col-lg-5" align="left">
				<div class="fs-4 fw-semibold text-decoration-underline">Stored
					Info:</div>
				<br />

				<div class="fs-6">
					<span class="fw-bold">Name: </span> ${storedInfo.name }
				</div>


				<div class="fs-6">
					<span class="fw-bold">Father's Name: </span>
					${storedInfo.father_name }
				</div>

				<div class="fs-6">
					<span class="fw-bold">Mother's Name: </span>
					${storedInfo.mother_name }
				</div>

				<div class="fs-6">
					<span class="fw-bold">Address: </span>${storedInfo.address }
				</div>

			</div>

		</div>


		<br />

		<div class="fs-5">
			<span class="fw-bold">Account Number: </span>
			${receivedInfo.account_number }
		</div>

		<div class="fs-4">
			<span class="fw-bold">To verify call: </span>${receivedInfo.phone_number }
		</div>

		<div align="center">

			<br /> <a class="btn btn-danger"
				href="confirm-choice?request=${request}"
				onclick="if(!(confirm('Do you want to REJECT the request of ${receivedInfo.name}, Account No: ${receivedInfo.account_number}?'))) return false">Reject</a>

			<a class="btn btn-success"
				href="updaterequestInfoForm?requestId=${receivedInfo.user_id}"
				onclick="if(!(confirm('Do you want to ACCEPT the request of ${receivedInfo.name}, Account No: ${receivedInfo.account_number}?'))) return false">
				Activate</a>
		</div>

	</div>

</body>
</html>