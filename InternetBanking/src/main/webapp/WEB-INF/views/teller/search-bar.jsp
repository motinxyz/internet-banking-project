<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>
<body>
	<div class="fs-3 text-center text-decoration-underline">TRANSACT</div>

	<br />

	<div class="col-lg-7" align="center">


		<div class="col-lg-11">
			<form:form action="search-accounts-for-transacting"
				modelAttribute="searchDTO" role="search">

				<div class="d-flex mb-2" align="center">

					<form:input path="account_number" class="form-control me-2 "
						type="text" placeholder="Account Number:" aria-label="Search" />

					<button class="btn btn-outline-dark" type="submit">Search</button>

				</div>

				<div>
					<form:errors class="text-danger" path="account_number"></form:errors>
				</div>
			</form:form>

			<c:if test="${account_is_frozen == true}">

				<div>
					<p class="text-danger mt-4">Sorry! This account is frozen!
					<p>
				</div>

			</c:if>



			<c:if test="${registered_user == false}">

				<div>
					<p class="text-danger mt-4">Invalid account number!
					<p>
				</div>

			</c:if>

		</div>

	</div>

</body>
</html>