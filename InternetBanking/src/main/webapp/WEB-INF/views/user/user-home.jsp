<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="user-header.jsp"%>

<%@include file="user-tabs.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<title>ABCB</title>

<style type="text/css">
.body {
	margin-top: 80px;
}
</style>
</head>

<body>

	<div class="container col-lg-8" align="center">

		<%@include file="user-header.jsp"%>
		<table class="col-lg-8 table table-bordered table-fixed">

			<caption>Account Details with Balance</caption>

			<thead>
				<tr class="">

					<th class="table-dark" scope="col"
						style="width: 10%; font-size: 15px; vertical-align: middle;">ACC/NO</th>
					<th class="table-dark" scope="col"
						style="width: 20%; font-size: 15px; vertical-align: middle;">TITLE</th>
					<th class="table-dark" scope="col"
						style="width: 20%; font-size: 15px; vertical-align: middle;">BALANCE</th>
					<th class="table-dark align-middle" scope="col"
						style="width: 20%; font-size: 15px; vertical-align: middle;">TRANSACTION
						HISTORIES</th>

				</tr>
			</thead>
			<tbody>

				<tr>
					<td>${account_number }</td>
					<td>${name }</td>
					<td>${balance }</td>
					<td align="center"><a class="text-dark"
						href="/InternetBanking/transaction-histories"> <i
							class="bi bi-file-break"></i>
					</a></td>
				</tr>

			</tbody>
		</table>
	</div>

</body>
</html>