<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Change password</title>
    </head>

    <body>

        <form method="post" action="changePass">
            <p>New password: <input type="text" name="pass"></p>
            <button type="submit">CHANGE</button>
        </form>

        <button onclick="window.location.href='${pageContext.request.contextPath}/assign'">Assign</button>

        <button onclick="window.location.href='${pageContext.request.contextPath}/login'">Log Out</button>

    </body>
</html>