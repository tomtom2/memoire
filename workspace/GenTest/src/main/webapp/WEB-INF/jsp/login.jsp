<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
<title>Funreco</title>
<!-- Bootstrap -->
<link href="/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
	<legend>Login</legend>
	<form action="/j_spring_security_check" class="form-horizontal" method='POST'>
		<div class="control-group">
			<label class="control-label" for="username">User</label>
			<div class="controls">
				<input name="j_username" type="text" id="username"
					placeholder="Username">
			</div>
		</div>
		<div class="control-group">
			<label class="control-label" for="password">Password</label>
			<div class="controls">
				<input name="j_password" type="password" id="password"
					placeholder="Password">
			</div>
		</div>
		<div class="control-group">
			<div class="controls">
				<button type="submit" class="btn">Login</button>
			</div>
		</div>
	</form>
</body>
</html>