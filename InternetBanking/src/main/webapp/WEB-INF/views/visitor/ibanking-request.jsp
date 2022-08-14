<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@include file="visitor-header.jsp"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>iBanking Request</title>

<style type="text/css">
.container {
	background: white;
	box-shadow: 15px 15px 20px;
	border-radius: 15px;
	box-shadow: 15px 15px 30px;
	margin-top: 50px;
	padding-bottom: 30px;
}

.link {
	text-decoration: none;
	transition: text-decoration 0.5s;
}

.link:hover {
	text-decoration: underline;
}
</style>

</head>
<body>
	<section
		class="container col-lg-7  d-flex align-items-center justify-content-center"
		style="padding-top: 40px;">

		<div align="center">

			<form:form action="submit-ibanking-request" method="post"
				modelAttribute="iBankingRequestDTO" class="row g-4 col-lg-9">

				<div>
					<h2 class="fs-2">iBanking Request Form</h2>
				</div>

				<div class="col-md-6" align="left">

					<form:input type="text" path="name" class="form-control"
						placeholder="Your Name:" />

					<form:errors path="name" class="text-danger" />
				</div>

				<div class="col-md-6" align="left">

					<form:input type="number" path="account_number"
						class="form-control" placeholder="Account Number:" />

					<form:errors path="account_number" class="text-danger" />
				</div>

				<div class="col-md-6" align="left">

					<form:input type="text" path="father_name" class="form-control"
						placeholder="Father's Name:" />

					<form:errors path="father_name" class="text-danger" />
				</div>


				<div class="col-md-6" align="left">

					<form:input type="text" path="mother_name" class="form-control"
						placeholder="Mother's Name:" />

					<form:errors path="mother_name" class="text-danger" />
				</div>

				<div class="col-md-12" align="left">

					<form:input type="text" path="address" class="form-control"
						placeholder="Address:" />

					<form:errors path="address" class="text-danger" />
				</div>

				<div class="col-md-6" align="left">

					<form:input type="email" path="email" class="form-control"
						placeholder="Registered Email:" />

					<form:errors path="email" class="text-danger" />
				</div>

				<div class="col-md-6" align="left">

					<form:input type="text" path="phone_number" class="form-control"
						placeholder="Registered Phone Number:" />

					<form:errors path="phone_number" class="text-danger" />
				</div>


				<div class="col-md-12" align="left">

					<form:input type="password" path="password" class="form-control"
						placeholder="Password:" />

					<form:errors path="password" class="text-danger" />
				</div>


				<div align="center">
					<div class="form-check form-switch col-lg-7">
						<form:checkbox path="termsAndConditionsChecked"
							class="form-check-input" id="checkbox" />

						<label for="checkbox"> I Agree to the <a class="link"
							href="#">Terms &amp; Conditions</a>
						</label>

					</div>

					<form:errors path="termsAndConditionsChecked" class="text-danger" />
				</div>

				<div class="col-12">
					<button type="submit" class="btn btn-dark">Submit Request</button>
				</div>

			</form:form>
		</div>
	</section>
</body>
</html>