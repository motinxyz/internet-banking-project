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
	margin-top: 90px;
	padding-top: 50px;
	padding-bottom: 50px;
}
</style>

</head>

<body>

	<div class="container col-lg-8 " align="center">

		<table class="col-lg-8 table table-bordered table-fixed">

			<caption>Cash Reserve</caption>

			<thead>
				<tr class="">

					<th class="table-dark" scope="col"
						style="width: 20%; font-size: 15px; vertical-align: middle;">CASH
						RESERVE</th>
					<th class="table-dark" scope="col"
						style="width: 20%; font-size: 15px; vertical-align: middle;">CASH
						DEPOSITED</th>
					<th class="table-dark" scope="col"
						style="width: 20%; font-size: 15px; vertical-align: middle;">CASH
						WITHDRAWN</th>
				</tr>
			</thead>
			<tbody>

				<tr>
					<td>${cashStat.cash_reserve }</td>
					<td>${cashStat.cash_deposited }</td>
					<td>${cashStat.cash_withdrawn }</td>
				</tr>

			</tbody>
		</table>

	</div>

</body>
</html>