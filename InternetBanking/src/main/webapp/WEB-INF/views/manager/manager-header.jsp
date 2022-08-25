<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../common-imports.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">
.dropdown-item {
	
}

.dropdown-item:hover {
	background-color: black;
	color: white;
	transition: 0.15s;
}
</style>
</head>

<!-- navigation bar -->

<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
	<div class="container-fluid">
		<a class="navbar-brand" href="/InternetBanking/cash-reserve"> <i
			class="bi bi-bank"> | ABCB</i>
		</a>

		<button class="navbar-toggler" type="button" data-bs-toggle="collapse"
			data-bs-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">

			<ul class="navbar-nav me-auto mb-2 mb-lg-0">

				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="/InternetBanking/cash-reserve">Home</a></li>

				<li class="nav-item"><a class="nav-link"
					href="/InternetBanking/employees">Employees</a></li>

				<li class="nav-item"><a class="nav-link"
					href="/InternetBanking/users">Users</a></li>


				<li class="nav-item"><a class="nav-link"
					href="/InternetBanking/transaction-logs">Transactions</a></li>

				<li class="nav-item"><a class="nav-link"
					href="/InternetBanking/activity-logs">Activities</a></li>
				<!-- 				<li class="nav-item"><a class="nav-link"
					href="/InternetBanking/#">Withdraw</a></li> -->


				<li class="nav-item"><a class="nav-link"
					href="/InternetBanking/contact-us">Contact Us</a></li>
			</ul>

			<ul class="navbar-nav mb-2 mb-lg-0" style="">

				<!-- 				<li class="nav-item fst-italic"><a class="nav-link active"
					aria-current="page" style=""> </a></li> -->

				<!-- dropdown -->

				<li class="nav-item dropdown fst-italic "><a
					class="nav-link dropdown-toggle" href="#" role="button"
					data-bs-toggle="dropdown" aria-expanded="false"> ${name } <i
						class="bi bi-person"></i>
				</a>

					<ul class="dropdown-menu ">

						<li><a class="dropdown-item" aria-current="page"
							href="/InternetBanking/user-info"> Personal Info</a></li>

						<li><a class="dropdown-item"
							href="/InternetBanking/security-info">Security Info</a></li>

						<li><a class="dropdown-item"
							href="/InternetBanking/update-password">Update Password</a></li>


						<li><hr class="dropdown-divider"></li>
						<li><a class="dropdown-item" href="/InternetBanking/sign-out">Sign
								Out &nbsp; &nbsp;<i class="bi bi-box-arrow-right"></i>
						</a></li>
					</ul></li>

				<!-- /dropdown -->
			</ul>
		</div>
	</div>
</nav>