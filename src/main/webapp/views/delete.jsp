<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 15.12.2019
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
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
