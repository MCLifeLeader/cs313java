<%-- 
    Document   : welcome
    Created on : Feb 28, 2017, 9:51:40 PM
    Author     : vmthompson - cs313 team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome</title>
    </head>
    <body>
        <h1>Welcome ${sessionScope.username}</h1>
        <h2>You did it! You broke into the Matrix!</h2>
        <br>
        <a href="Logout">Logout</a>
    </body>
</html>
