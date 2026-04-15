<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
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