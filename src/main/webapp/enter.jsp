<!DOCTYPE html>
<html>

<head>
    <meta charset="UTF-8"/>
    <title>Enter page</title>
</head>

<body>

<h2>Welcome to enter page ${login}</h2>

<c:choose>
    <c:when role="${'USER ADMIN'}">
        <form method="post">
            <button type="submit">Admin panel</button>
        </form>
    </c:when>
</c:choose>




</body>
</html>