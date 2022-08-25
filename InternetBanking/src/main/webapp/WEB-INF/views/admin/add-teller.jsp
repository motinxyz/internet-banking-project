<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="admin-header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Add Teller</title>

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
}

.link:hover {
	text-decoration: underline;
	transition: 0.15s;
}
</style>

</head>
<body>

	<section
		class="container col-lg-7  d-flex align-items-center justify-content-center"
		style="padding-top: 40px;">

		<div align="center">

			<form:form action="save-teller" method="post"
				modelAttribute="addTellerDTO" class="row g-4 col-lg-9">

				<div>
					<h2 class="fs-2 text-decoration-underline">New Teller Info</h2>
				</div>

				<div class="col-md-6" align="left">

					<form:input type="text" path="name" class="form-control"
						placeholder="Your Name:" required="true" />

					<form:errors path="name" class="text-danger" />
				</div>


				<div class="col-md-6" align="left">

					<form:input type="text" path="father_name" class="form-control"
						placeholder="Father's Name:" required="true" />

					<form:errors path="father_name" class="text-danger" />
				</div>


				<div class="col-md-6" align="left">

					<form:input type="text" path="mother_name" class="form-control"
						placeholder="Mother's Name:" required="true" />

					<form:errors path="mother_name" class="text-danger" />
				</div>

				<div class="col-md-6 text-secondary" align="left"
					style="line-height: 24px; font-size: 19px;">

					<label for="birthday">Birthday:</label>
					<form:input type="date" path="date_of_birth" id="birthday"
						name="birthday" />
				</div>

				<div class="col-md-12" align="left">

					<form:input type="text" path="address" class="form-control"
						placeholder="Address:" required="true" />

					<form:errors path="address" class="text-danger" />
				</div>


				<div class="col-md-6" align="left">

					<form:input type="email" path="email" class="form-control"
						placeholder="Registered Email:" required="true" />

					<form:errors path="email" class="text-danger" />
				</div>


				<div class="col-md-6" align="left">

					<form:input type="number" path="phone_number" class="form-control"
						placeholder="Registered Phone Number:" required="true" />

					<form:errors path="phone_number" class="text-danger" />
				</div>


				<div class="col-md-12" align="left">

					<form:input type="password" path="password" class="form-control"
						placeholder="Password:" required="true" />

					<form:errors path="password" class="text-danger" />
				</div>


				<div class="col-12">
					<button type="submit" class="btn btn-dark">Add Teller</button>
				</div>

			</form:form>

			<div class="text-success mt-3 ">
				<c:if test="${tellerAdded == true }">
					<p>Teller added successfully!</p>
				</c:if>
			</div>

			<div class="text-danger mt-3">
				<c:if test="${duplicateTeller == true }">
					<p>Duplicate teller!</p>
				</c:if>
			</div>

		</div>
	</section>
</body>
</html>