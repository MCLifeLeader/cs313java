<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <h1>Welcome <%= request.getSession().getAttribute("username") %></h1>
        <h2>You did it! You broke into the Matrix!</h2>
        <br>
        <a href="Logout">Logout</a>
    </body>
</html>
