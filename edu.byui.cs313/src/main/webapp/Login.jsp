<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Login</title>
	</head>
	<body>
		<c:if test="$(not empty error)">
			<p>${error}</p>
		</c:if>
	    <form id="form" action="login" method="POST">
	      <label for="book">Username:</label>
	      <input type="text" name="txtUsername" id="txtUsername"><br>
	      <label for="chapter">Password:</label>
	      <input type="text" name="txtPassword" id="txtPassword"><br>
	      <br>
	      <input type="submit" value="Login" />
	    </form>
	</body>
</html>