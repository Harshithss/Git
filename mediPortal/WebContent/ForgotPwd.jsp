<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<!-- jQuery library -->
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>

<!-- Latest compiled JavaScript -->
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</head>
<style>
body {
	background-color: honeydew;
}

*[role="form"] {
	max-width: 530px;
	padding: 15px;
	margin: 0 auto;
	background-color: #fff;
	border-radius: 0.3em;
	align-items: center;
}
</style>
<body>
	<div class="container">
		<h5>${message}</h5>
		<form action="ForgotPwd.nan" method="post" role="form"
			class="form-horizontal">

			<div class="form-group">
				<label for="email" class="col-sm-6">Employee Mail</label>
				<div class="col-sm-9">
					<input type="text" name="email" id="email" class="form-control">
				</div>
			</div>
			
			<div class="form-group">
				<button type="submit" class="btn btn-primary">Reset</button>
			</div>
			</form>
			</div>
</body>
</html>