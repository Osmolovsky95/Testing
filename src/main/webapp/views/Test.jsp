<%@ page import="java.io.PrintWriter" %>
<%@ page import="sun.invoke.util.BytecodeName" %><%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 18.12.2019
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>Тест</title>
</head>
<body>
<form method="post">
    <p><b>MyQuestion   ${question}</b></p>
    <p><input type= "checkbox" name="0" value="0">${answer0}<Br>
        <input type="checkbox" name="1" value="1">${answer1}<Br>
        <input type="checkbox" name="2" value="2">${answer2}<Br>
        <input type="checkbox" name="3" value="3">${answer3}<Br>
    <p><input type="submit" value="Отправить"></p>
</form>
</body>
</html>
