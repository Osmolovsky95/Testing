<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 18.12.2019
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%PrintWriter printWriter = response.getWriter();%>
<html>
<head>
    <title>Тест</title>
</head>
<body>
<form method="post">
    <p><b>С какими операционными системами вы знакомы?</b></p>
    <p><input type= "checkbox" name="option1" value="a1" checked>Windows 95/98<Br>
        <input type="checkbox" name="option2" value="a2">Windows 2000<Br>
        <input type="checkbox" name="option3" value="a3">System X<Br>
        <input type="checkbox" name="option4" value="a4">Linux<Br>
        <input type="checkbox" name="option5" value="a5">X3-DOS</p>
    <p><input type="submit" value="Отправить"></p>
</form>
</body>
</html>
