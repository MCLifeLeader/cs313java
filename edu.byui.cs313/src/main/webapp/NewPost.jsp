<%@page contentType="text/html" pageEncoding="UTF-8" session="true"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>New Post</title>
</head>
<body>
	<h2>New Posts!</h2>

    <a href="ViewPost">View Posts</a><br/>

    <form action="EnterPost" method="POST">
        Post Comment Here: <textarea id="comment" name="comment"></textarea><br>
        <br>
        <input type="submit" value="Submit">
    </form>
    
</body>
</html>