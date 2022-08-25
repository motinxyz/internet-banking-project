<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="manager-header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transactions</title>

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

					<th class="table-dark" scope="col" style="width: 8%;">Trx ID:</th>

					<th class="table-dark" scope="col" style="width: 10%;">Teller
						ID:</th>

					<th class="table-dark" scope="col" style="width: 12%;">A/C No:</th>

					<th class="table-dark" scope="col" style="width: 12%;">Trx
						Type:</th>

					<th class="table-dark" scope="col" style="width: 8%;">Amount:</th>
					<th class="table-dark" scope="col" style="width: 10%;">Status:</th>
					<th class="table-dark" scope="col" style="width: 20%;">Time:</th>
				</tr>
			</thead>
			<tbody id="transactions">


				<c:forEach var="transaction" items="${transactionLogs}">
					<tr>

						<td style="vertical-align: middle;">${transaction.transaction_id}</td>
						<td style="vertical-align: middle;">${transaction.teller_id}</td>
						<td style="vertical-align: middle;">${transaction.account_number}</td>
						<td style="vertical-align: middle;">${transaction.transaction_type}</td>
						<td style="vertical-align: middle;">${transaction.amount}</td>
						<td style="vertical-align: middle;">${transaction.status}</td>
						<td style="vertical-align: middle;">${transaction.transaction_time}</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

<script>
$(document).ready(function(){
  $("#search_keyword").on("keyup", function() {
    var value = $(this).val().toLowerCase();
    $("#transactions tr").filter(function() {
      $(this).toggle($(this).text().toLowerCase().indexOf(value) > -1)
    });
  });
});
</script>

</body>
</html>