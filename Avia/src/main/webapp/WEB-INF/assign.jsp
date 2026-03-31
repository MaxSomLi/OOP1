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
                <th>Status</th>
            </tr>

            <c:forEach var="f" items="${flights}">
                <tr>
                    <td>${f.id}</td>
                    <td>${f.flightNumber}</td>
                    <td>${f.origin}</td>
                    <td>${f.destination}</td>
                    <td>${f.status}</td>
                </tr>
            </c:forEach>
        </table>

        <form method="post" action="deleteFlight">
            <p>ID = <input type="number" name="id" required></p>
            <button type="submit">Remove</button>
        </form>

        <form method="post" action="addFlight">
            <p>№ = <input type="text" name="number" required></p>
            <p>Origin = <input type="text" name="origin" required></p>
            <p>Destination = <input type="text" name="destination" required></p>
            <p>Status = <input type="text" name="status" required></p>
            <button type="submit">Add</button>
        </form>

        <table border="1">
            <tr>
                <th>ID</th>
                <th>Name</th>
                <th>Role</th>
            </tr>

            <c:forEach var="f" items="${crewMembers}">
                <tr>
                    <td>${f.id}</td>
                    <td>${f.name}</td>
                    <td>${f.role}</td>
                </tr>
            </c:forEach>
        </table>

        <form method="post" action="deleteCrew">
            <p>ID = <input type="number" name="id" required></p>
            <button type="submit">Remove</button>
        </form>

        <form method="post" action="addCrew">
            <p>Name = <input type="text" name="name" required></p>
            <p>Role = <input type="text" name="role" required></p>
            <button type="submit">Add</button>
        </form>

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

    </body>
</html>