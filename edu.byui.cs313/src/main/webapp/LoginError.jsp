<%-- 
    Document   : login
    Created on : Feb 28, 2017, 9:42:20 PM
    Author     : vmthompson - cs313 team
--%>

<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Error</title>
    </head>
    <body>
    <h1>Login Error</h1>
<!--     
        <c:if test="${not empty error}">
            <p><%= request.getSession().getAttribute("username") %></p>
        </c:if>
-->   
        <form action="Login" method="POST">
            Username: <input type="text" name="username"><br>
            Password: <input type="password" name="password"><br>
            <br>
            <input type="submit" value="Submit">
        </form>
    </body>
</html>
