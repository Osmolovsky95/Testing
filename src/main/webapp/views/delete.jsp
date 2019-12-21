<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 15.12.2019
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Удалить студента</title>
</head>
<body>
<form method="post">
    <label>Имя:
        <input type="text" name="name"><br />
    </label>
    <button onclick="Location.href='/Testing_war/delete'">Удалить</button>
</form>
<div>
    <%
        if (request.getAttribute("name") != null) {
            out.println("<p>User '" + request.getAttribute("name") + "' removed!</p>");
        }
    %>
</div>
</body>
</html>
