<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="manager-header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Activities</title>

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"></script>



</head>
<body>

	<div class="container col-lg-7">


		<input class="form-control" id="search_keyword" type="text"
			placeholder="Search.."> <br>

		<table class="table table-hover table-fixed">

			<thead>
				<tr class="">

					<th class="table-dark" scope="col" style="width: 8%;">Activity
						ID:</th>
					<th class="table-dark" scope="col" style="width: 8%;">User ID:</th>

					<th class="table-dark" scope="col" style="width: 10%;">Activity:</th>
					<th class="table-dark" scope="col" style="width: 10%;">Status:</th>
					<th class="table-dark" scope="col" style="width: 20%;">Time:</th>
				</tr>
			</thead>
			<tbody id="activities">


				<c:forEach var="activity" items="${activityLogs}">
					<tr>

						<td style="vertical-align: middle;">${activity.activity_id}</td>
						<td style="vertical-align: middle;">${activity.user_id}</td>
						<td style="vertical-align: middle;">${activity.activity_type}</td>
						<td style="vertical-align: middle;">${activity.activity_status}</td>
						<td style="vertical-align: middle;">${activity.activity_time}</td>
					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

<script>
$(document).ready(function(){
  $("#search_keyword").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#activities tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>

</body>
</html>