<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" isELIgnored="false"%>


<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">

<style type="text/css">
.container {
	background: white;
	box-shadow: 15px 15px 20px;
	border-radius: 15px;
	box-shadow: 15px 15px 30px;
	margin-top: 80px;
	padding-top: 50px;
	padding-bottom: 50px;
}

.messageUsIcon {
	
}

.messageUsIcon:hover {
	cursor: pointer;
	color: rgba(24, 72, 90, 1);
}
</style>
</head>
<body style="margin-top: 120px;">

	<div class="container col-lg-5" align="center">

		<div class="fs-3 fw-semibold text-decoration-underline">Contacts</div>
		<br />

		<div class="fs-5">
			Help line: <span class="fs-5 text-primary">09601010101</span>
		</div>

		<div class="fs-5">
			For issues: <span class="fs-5 text-primary">help@abcb.com</span>
		</div>

		<div class="fs-5">
			For queries: <span class="fs-5 text-primary">info@abcb.com</span>
		</div>




		<br />

		<div class="fs-5">Or leave us a message:</div>

		<div class="messageUsIcon fs-4">
			<i class="bi bi-envelope-paper" class="btn btn-dark"
				data-bs-toggle="modal" data-bs-target="#exampleModal"
				data-bs-whatever="@mdo"></i>
		</div>

	</div>



	<div class="modal fade" id="exampleModal" tabindex="-1"
		aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="exampleModalLabel">New message</h5>
					<button type="button" class="btn-close" data-bs-dismiss="modal"
						aria-label="Close"></button>
				</div>
				<div class="modal-body">
					<form>

						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">Your
								Name:</label> <input type="text" class="form-control"
								id="recipient-name">
						</div>

						<div class="mb-3">
							<label for="recipient-name" class="col-form-label">Your
								Email:</label> <input type="text" class="form-control"
								id="recipient-name">
						</div>
						<div class="mb-3">
							<label for="message-text" class="col-form-label">Message:</label>
							<textarea class="form-control" id="message-text"></textarea>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-secondary"
						data-bs-dismiss="modal">Close</button>
					<button type="button" class="btn btn-dark">Send message</button>
				</div>
			</div>
		</div>
	</div>


</body>
</html>