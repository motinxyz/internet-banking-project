<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="ibAdmin-header.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Pending iBanking Requests</title>
</head>
<body>


	<div class="container ">

		<table class="table table-hover table-fixed">

			<thead>
				<tr class="">

					<th class="table-dark" scope="col" style="width: 10%;">User
						ID:</th>
					<th class="table-dark" scope="col" style="width: 10%;">A/c No:</th>
					<th class="table-dark" scope="col" style="width: 20%;">Name:</th>
					<th class="table-dark" scope="col" style="width: 30%;">Address:</th>
					<th class="table-dark" scope="col" style="width: 15%;">Phone
						No:</th>
					<th class="table-dark" scope="col" style="width: 10%;"></th>

				</tr>
			</thead>
			<tbody>

			</tbody>

			<c:forEach var="request" items="${requestsList}">
				<tr>

					<td style="vertical-align: middle;">${request.user_id}</td>
					<td style="vertical-align: middle;">${request.account_number}</td>
					<td style="vertical-align: middle;">${request.name}</td>
					<td style="vertical-align: middle;">${request.address}</td>
					<td style="vertical-align: middle;">${request.phone_number}</td>


					<td><a class="btn btn-dark btn-sm"
						href="view-request-details/${request.account_number}">Details</a></td>

				</tr>
			</c:forEach>

		</table>
	</div>
</body>
</html>