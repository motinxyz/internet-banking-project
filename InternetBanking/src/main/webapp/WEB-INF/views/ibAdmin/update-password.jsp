<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="ibAdmin-header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>update Password</title>

<style type="text/css">
body {
	padding-top: 50px;
}

.container {
	background: white;
	box-shadow: 15px 15px 20px;
	border-radius: 15px;
	box-shadow: 15px 15px 30px;
	padding-top: 20px;
	padding-bottom: 50px;
}

.goBackIcon {
	font-size: 1.5rem;
}

.goBackIcon:hover {
	cursor: pointer;
}
</style>



</head>
<body>

	<div align="center">

		<form:form action="process-update-password-request" method="post"
			modelAttribute="updatePasswordDTO" class="row g-4 col-lg-9">


			<div class="container col-lg-6" align="center">

				<c:if test="${password_updated_successfully != true}">

					<div class="goBackIcon" align="left">
						<a href="/InternetBanking/"> <i
							class="bi bi-arrow-left-circle"></i>
						</a>
					</div>

					<div>
						<h2 class="fs-3 text-decoration-underline">Update Password</h2>
					</div>

				</c:if>


				<c:if test="${password_updated_successfully == true}">

					<div style="padding-top: 40px;">
						<h2 class="fs-3 text-decoration-underline">Update Password</h2>
					</div>

				</c:if>



				<br />

				<div class="col-md-8 mb-3" align="left">

					<form:input type="password" path="oldPassword"
						class="form-control " placeholder="Old Password:" required="true" />

					<form:errors path="oldPassword" class="text-danger" />

					<c:if test="${old_password_matched == false}">
						<p class="text-danger">Old password doesn't match!</p>
					</c:if>
				</div>

				<div class="col-md-8 mb-3" align="left">

					<form:input type="password" path="newPassword" class="form-control"
						placeholder="New Password:" required="true" />

					<form:errors path="newPassword" class="text-danger" />

				</div>

				<div class="col-md-8 mb-3" align="left">

					<form:input type="password" path="confirmPassword"
						class="form-control" placeholder="Confirm Password:"
						required="true" />

					<form:errors path="confirmPassword" class="text-danger" />

					<c:if test="${confirm_password_matched == false}">
						<p class="text-danger">Does not matches with the new password!</p>
					</c:if>
				</div>


				<c:if test="${new_password_is_same_as_old_password == true}">
					<p class="text-danger">New password is same as old password!</p>
				</c:if>

				<c:if test="${password_updated_successfully == true}">
					<p class="text-success">Your password has been updated
						successfully!</p>


					<div class="goBackIcon" align="center">
						<a href="/InternetBanking/" style="padding-top: 30px;"> <i
							class="bi bi-arrow-left-circle">Go Home</i>
						</a>
					</div>

				</c:if>

				<c:if test="${password_updated_successfully != true}">

					<input type="submit" class="btn btn-dark">

				</c:if>


			</div>

		</form:form>

	</div>
</body>
</html>