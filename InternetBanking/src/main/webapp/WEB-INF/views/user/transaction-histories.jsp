<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="user-header.jsp"%>

<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Transactions</title>

<style type="text/css">
.goBackIcon {
	font-size: 1.4rem;
	margin-bottom: 10px;
}

.goBackIcon:hover {
	cursor: pointer;
}
</style>

</head>
<body>

	<div class="container col-lg-7">

		<div class="goBackIcon" align="left">
			<a class="text-dark" href="/InternetBanking/"> <i
				class="bi bi-arrow-left-circle"></i>
			</a>
		</div>

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
					<th class="table-dark" scope="col" style="width: 20%;">Time:</th>
				</tr>
			</thead>
			<tbody>


				<c:forEach var="transaction" items="${transactionHistories}">
					<tr>

						<td style="vertical-align: middle;">${transaction.transaction_id}</td>
						<td style="vertical-align: middle;">${transaction.teller_id}</td>
						<td style="vertical-align: middle;">${transaction.account_number}</td>
						<td style="vertical-align: middle;">${transaction.transaction_type}</td>
						<td style="vertical-align: middle;">${transaction.amount}</td>
						<td style="vertical-align: middle;">${transaction.transaction_time}</td>

					</tr>
				</c:forEach>

			</tbody>
		</table>
	</div>

</body>
</html>