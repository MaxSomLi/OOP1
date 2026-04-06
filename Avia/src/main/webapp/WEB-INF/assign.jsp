<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
    <head>
        <title>Assign</title>
    </head>

    <body>
        <table border="1">
            <tr>
                <th>ID</th>
                <th>Flight Number</th>
                <th>Origin</th>
                <th>Destination</th>
            </tr>

            <c:forEach var="f" items="${flights}">
                <tr>
                    <td>${f.id}</td>
                    <td>${f.flightNumber}</td>
                    <td>${f.origin}</td>
                    <td>${f.destination}</td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${sessionScope.userRole > 0}">
            <form method="post" action="deleteFlight">
                <p>ID = <input type="number" name="id" required></p>
                <button type="submit">Remove</button>
            </form>

            <form method="post" action="addFlight">
                <p># = <input type="text" name="number" required></p>
                <p>Origin = <input type="text" name="origin" required></p>
                <p>Destination = <input type="text" name="destination" required></p>
                <button type="submit">Add</button>
            </form>
        </c:if>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Admin</th>
            </tr>

            <c:forEach var="f" items="${crewMembers}">
                <tr>
                    <td>${f.id}</td>
                    <td>${f.name}</td>
                    <td>${f.admin}</td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${sessionScope.userRole == 2}">
            <form method="post" action="deleteCrew">
                <p>ID = <input type="number" name="id" required></p>
                <button type="submit">Remove</button>
            </form>
        </c:if>

        <c:if test="${sessionScope.userRole > 0}">
            <form method="post" action="addCrew">
                <p>Name = <input type="text" name="name" required></p>
                <p>Admin<input type="checkbox" name="is_admin"></p>
                <p>Password = <input type="text" name="password" required></p>
                <button type="submit">Add</button>
            </form>
        </c:if>

        <table border="1">
            <tr>
                <th>Flight ID</th>
                <th>Crew ID</th>
            </tr>

            <c:forEach var="f" items="${flightCrews}">
                <tr>
                    <td>${f.flightId}</td>
                    <td>${f.crewId}</td>
                </tr>
            </c:forEach>
        </table>

        <c:if test="${sessionScope.userRole > 0}">
            <form method="post" action="deleteJoin">
                <p>Flight ID = <input type="number" name="id1" required></p>
                <p>Crew ID = <input type="number" name="id2" required></p>
                <button type="submit">Remove</button>
            </form>

            <form method="post" action="addJoin">
                <p>Flight ID = <input type="number" name="id1" required></p>
                <p>Crew ID = <input type="number" name="id2" required></p>
                <button type="submit">Add</button>
            </form>
        </c:if>

        <c:if test="${sessionScope.userRole == 2}">
            <c:forEach var="f" items="${users}">
                <form method="post" action="verify">
                    <h6 name="id">${f.id}</h6>
                    <input type="hidden" name="id" value="${f.id}">
                    <p>${f.name}</p>
                    <p>Admin<input type="checkbox" name="is_admin"></p>
                    <p>Verify<input type="checkbox" name="verify"></p>
                    <button type="submit">*</button>
                </form>
            </c:forEach>
        </c:if>

        <c:if test="${sessionScope.userRole < 2}">
            <button onclick="window.location.href='${pageContext.request.contextPath}/flights'">My flights</button>
            <button onclick="window.location.href='${pageContext.request.contextPath}/pass'">Change password</button>
        </c:if>

        <button onclick="window.location.href='${pageContext.request.contextPath}/login'">Log Out</button>

    </body>
</html>