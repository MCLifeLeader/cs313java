<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Movie Search</title>
    </head>
    <body>
        <h1>Search for a Moobie</h1>
        <form action="MovieSearch" method="POST">
            Search for a Movie: <input type="text" name="search"><br><br>
            <input type="submit" value="Search">
        </form>
        
        <div id="results">
            <c:forEach var="movie" items="${movies}">
                <p><a href="MovieDetails?id=${movie.imdbID}">${movie.Title}</a></p>
            </c:forEach>
        </div>
    </body>
</html>
