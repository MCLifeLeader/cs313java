<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Details</title>
    </head>
    <body>
        <h1>Details for: ${movie.Title}</h1>
        <p>${movie.Plot}</p>
        <img src="${movie.Poster}">
    </body>
</html>
