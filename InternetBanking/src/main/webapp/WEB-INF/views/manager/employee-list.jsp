<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="manager-header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Employees</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>

</head>
<body>

	<div class="container ">

		<input class="form-control" id="search_keyword" type="text"
			placeholder="Search.."> <br>

		<table class="table table-hover table-fixed">

			<thead>
				<tr class="">

					<th class="table-dark" scope="col" style="width: 8%;">User ID:</th>
					<th class="table-dark" scope="col" style="width: 20%;">Name:</th>

					<th class="table-dark" scope="col" style="width: 20%;">Address:</th>
					<th class="table-dark" scope="col" style="width: 12%;">Phone
						No:</th>
					<th class="table-dark" scope="col" style="width: 20%;">Email:</th>
					<th class="table-dark" scope="col" style="width: 10%;">Employee:</th>
					<th class="table-dark" scope="col" style="width: 8%;">Deactivated:</th>

					<th class="table-dark" scope="col" style="width: 10%;"></th>

				</tr>
			</thead>
			<tbody id="employee_list">

				<c:forEach var="employee" items="${employeeList}">
					<tr>

						<td style="vertical-align: middle;">${employee.user_id}</td>
						<td style="vertical-align: middle;">${employee.name}</td>
						<td style="vertical-align: middle;">${employee.address}</td>
						<td style="vertical-align: middle;">${employee.phone_number}</td>
						<td style="vertical-align: middle;">${employee.email}</td>
						<td style="vertical-align: middle;">${employee.permission_type}</td>
						<td style="vertical-align: middle;">${employee.frozen}</td>

						<td><a class="btn btn-dark btn-sm"
							href="view-employee-details/${employee.user_id}">Details</a></td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

<script>
$(document).ready(function(){
  $("#search_keyword").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#employee_list tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>

</body>
</html>