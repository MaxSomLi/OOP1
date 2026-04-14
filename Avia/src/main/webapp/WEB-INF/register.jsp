<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <title>Register</title>
    </head>
    <body>
        <form method="post" action="addUser">
            <p>Name = <input type="text" name="name" required></p>
            <p>Password = <input type="text" name="password" required></p>
            <button type="submit">Add</button>
        </form>
    </body>
</html>