<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@include file="visitor-header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%><!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Sign In</title>


<style type="text/css">
.row {
	background: white;
	box-shadow: 15px 15px 20px;
	border-radius: 15px;
	box-shadow: 15px 15px 30px;
}
</style>

</head>
<body style="margin-top: 60px;">
	<!-- 	<section class="signin py-5 bg-light"
		style="padding-top: 50px; align-content: center;"> -->
	<div
		class="container d-flex align-items-center justify-content-center align-middle"
		style="padding-top: 50px;">

		<div
			class="row col-lg-5 align-items-center justify-content-center align-middle"
			style="padding-top: 40px; padding-bottom: 30px;">

			<!-- 			<div class="col-lg-7"> -->

			<h1 align="center" style="padding-bottom: 25px;">Sign In</h1>

			<div align="center">

				<form:form class="col-lg-8" action="authenticate" method="post"
					modelAttribute="signInDTO">

					<div class="form-floating mb-3" align="left">
						<form:input type="email" path="email" class="form-control"
							id="email" placeholder="name@example.com" />
						<label for="email">Email:</label>

						<form:errors path="email" class="text-danger" />
					</div>

					<div class="form-floating mb-3" align="left">
						<form:input type="password" path="password" class="form-control"
							id="password" placeholder="********" />
						<label for="password">Password:</label>

						<form:errors path="password" class="text-danger" />
					</div>

					<form:hidden path="user_id" />

					<!-- 					<div class="form-check form-switch">
						<input class="form-check-input" type="checkbox" id="agreed" /> <label
							class="form-check-label" for="agreed">I Agree to the
							Terms &amp; Conditions</label>
					</div> -->

					<div class="col-12 " style="margin-top: 20px;">
						<button type="submit" class="btn btn-dark">Sign in</button>
					</div>
				</form:form>


				<div style="margin-top: 20px; text-decoration: none;">

					<c:if test="${validUser == false}">
						<p class="text-danger" style="margin-bottom: 10px;">Invalid
							Email/Password</p>
					</c:if>

					<a href="#" class="link-danger fw-bolder"
						style="text-decoration: none;">Forgot Password?</a>

				</div>

				<br />

				<div class="col-lg-9">
					<p class="fw-bolder">
						If you already have an account with us, you can request for
						iBanking Activation from <a href="ibanking-request"
							class="link-secondary">iBanking Request</a>
					</p>

				</div>

			</div>

		</div>

	</div>
	<!-- 	</section> -->
</body>
</html>