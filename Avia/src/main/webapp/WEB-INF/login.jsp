<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
    <head>
        <title>Enter</title>
    </head>
    <body>
        <h2>Auth</h2>
        <form action="${pageContext.request.contextPath}/login" method="post">
            <input type="text" name="login">
            <input type="password" name="password">
            <button type="submit">Login</button>
        </form>

        <% if(request.getParameter("error") != null) { %>
            <p style="color:red;">Wrong login info</p>
        <% } %>

        <button onclick="window.location.href='${pageContext.request.contextPath}/register'">Register</button>

    </body>
</html>