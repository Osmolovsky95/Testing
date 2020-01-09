<%@ page import="java.io.PrintWriter" %>
<%@ page import="sun.invoke.util.BytecodeName" %><%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 18.12.2019
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<html>
<head>
    <title>Тест</title>
</head>
<body>
<p>Добро пожаловать ${name}</p>
<form method="GET">
    <p><b>   ${question}</b></p>
    <p><input type= "radio" name="0" value=${answer_id_0} >${answer0} <Br>
        <input type="radio" name="0" value=${answer_id_1}>${answer1}<Br>
        <input type="radio" name="0" value= ${answer_id_2}>${answer2}<Br>
        <input type="radio" name="0" value=${answer_id_3}>${answer3}<Br>
    <p><input type="submit" value="${nameButton}"></p>
</form>
</body>
</html>
