<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="../common-imports.jsp"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
</head>

<!-- navigation bar -->

<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
	<div class="container-fluid">
		<a class="navbar-brand" href="<%=request.getContextPath()%>/"> <i
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
					aria-current="page" href="/InternetBanking/">Home</a></li>
				<li class="nav-item"><a class="nav-link" href="#">iBanking
						Requests</a></li>
			</ul>

			<ul class="navbar-nav mb-2 mb-lg-0">
				<li class="nav-item"><a class="nav-link active"
					aria-current="page" href="sign-out">Sign Out</a></li>
			</ul>

		</div>
	</div>
</nav>