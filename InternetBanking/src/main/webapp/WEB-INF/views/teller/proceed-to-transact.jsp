<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="teller-header.jsp"%>

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
	padding-top: 20px;
	padding-bottom: 50px;
}

.goBackIcon {
	font-size: 1.5rem;
}

.goBackIcon:hover {
	cursor: pointer;
}
</style>

</head>

<body>

	<div class="container col-lg-7" align="center">

		<div class="goBackIcon" align="left">
			<a href="/InternetBanking/transact"> <i
				class="bi bi-arrow-left-circle"></i>
			</a>
		</div>

		<div class="fs-3 text-center text-decoration-underline">USER</div>

		<br />
		<%-- 		<c:if test="${not empty user_info}"> --%>

		<div style="margin-top: 0px;">
			<hr>
		</div>

		<div class="col-lg-8 fs-6 lh-lg" align="left">

			<div>
				<span class="fw-bold">Account Number: </span>
				&nbsp;${account_info.account_number }
			</div>

			<div>
				<span class="fw-bold">Name: </span> &nbsp; &nbsp; &nbsp; &nbsp;
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; ${user_info.name }
			</div>

			<div>
				<span class="fw-bold">Balance: </span> &nbsp; &nbsp; &nbsp; &nbsp;
				&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;${account_info.balance }
			</div>

		</div>

		<div style="margin-top: 20px;">
			<hr>
		</div>

		<div class="col-lg-8 justify-content-center" style="margin-top: 40px;">

			<form:form action="proceed-to-transact"
				modelAttribute="transactionDTO" method="post">

				<form:hidden path="accountNumber"
					value="${account_info.account_number }" />


				<form:select path="action"
					class="btn btn-secondary dropdown-toggle mb-2" id="action">

					<form:option class="dropdown-item" value="deposit">Deposit</form:option>
					<form:option class="dropdown-item" value="withdraw">Withdraw</form:option>
				</form:select>

				<div>
					<form:errors class="text-danger" path="action" />
				</div>

				<form:input path="amount" type="number" placeholder="Amount:"
					class="col-lg-3 form-control mb-2" />

				<div>
					<form:errors id="amount" class="text-danger mb-2" path="amount" />
				</div>

				<input type="submit" class="btn btn-dark ms-2" value="Transact"
					onclick="if(!(confirm('Are you sure?'))) return false">

			</form:form>
		</div>

		<div>

			<c:if test="${validAmount == false }">
				<p class="text-danger">Invalid Amount</p>
			</c:if>

			<c:if test="${transactionSuccessful == false }">
				<p class="text-danger">Transaction Failed</p>
			</c:if>

			<c:if test="${transactionSuccessful == true }">
				<p class="text-success">
					Transaction Successful
					<%-- , Transaction ID:
					${transaction_id } --%>
				</p>
			</c:if>

		</div>


	</div>
</body>
</html>