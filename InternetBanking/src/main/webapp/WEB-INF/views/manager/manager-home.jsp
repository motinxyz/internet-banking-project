<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="manager-header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>ABCB</title>

<style type="text/css">
.container {
	background: white;
	box-shadow: 15px 15px 20px;
	border-radius: 15px;
	box-shadow: 15px 15px 30px;
	margin-top: 90px;
	padding-top: 50px;
	padding-bottom: 50px;
}
</style>

</head>

<%-- <body>

	<div class="container col-lg-7" align="center">

		<div class="fs-3 text-center text-decoration-underline">TRANSACT</div>

		<br />

		<div class="col-lg-7" align="center">

			<nav class="navbar bg-light d-flex justify-content-center">
				<div class="container-fluid">

					<form:form action="search-user-for-transacting"
						modelAttribute="searchDTO" class="d-flex" role="search">

						<form:input path="account_number"
							class="form-control me-2 col-md-10" type="search"
							placeholder="Account Number:" aria-label="Search" />

						<button class="btn btn-outline-dark" type="submit">Search</button>
						<form:errors path="account_number"></form:errors>
					</form:form>

				</div>
			</nav>

		</div>



	</div> --%>
</body>
</html>