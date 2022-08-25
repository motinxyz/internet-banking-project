<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="admin-header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Users</title>
</head>
<body>

	<div class="container ">

		<table class="table table-hover table-fixed">

			<thead>
				<tr class="">

					<th class="table-dark" scope="col" style="width: 8%;">User ID:</th>
					<th class="table-dark" scope="col" style="width: 20%;">Name:</th>

					<th class="table-dark" scope="col" style="width: 20%;">Address:</th>
					<th class="table-dark" scope="col" style="width: 12%;">Phone
						No:</th>
					<th class="table-dark" scope="col" style="width: 20%;">Email:</th>
					<th class="table-dark" scope="col" style="width: 8%;">Deactivated:</th>

					<th class="table-dark" scope="col" style="width: 10%;"></th>

				</tr>
			</thead>
			<tbody>


				<c:forEach var="user" items="${userList}">
					<tr>

						<td style="vertical-align: middle;">${user.user_id}</td>
						<td style="vertical-align: middle;">${user.name}</td>
						<td style="vertical-align: middle;">${user.address}</td>
						<td style="vertical-align: middle;">${user.phone_number}</td>
						<td style="vertical-align: middle;">${user.email}</td>
						<td style="vertical-align: middle;">${user.frozen}</td>

						<td><a class="btn btn-dark btn-sm"
							href="view-user-details/${user.user_id}">Details</a></td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

</body>
</html>