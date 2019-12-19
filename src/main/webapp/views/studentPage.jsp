<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 18.12.2019
  Time: 17:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Data.Student</title>
</head>
<body>
<form method="post">
    <label>Имя:
        <input type="text" name="name"><br />
    </label>
    <label>Пароль:
        <input type="password" name="pass"><br />
    </label>
    <button onclick="Location.href='studentPage/Test'">Войти</button>
</form>
</body>
</html>
