<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 18.12.2019
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>IT -academy students</title>
</head>
<body>
<div>
    <h1>IT - academy </h1>
</div>
<% PrintWriter printWriter=response.getWriter();
printWriter.println("<h1>Добро пожаловать "+ request.getParameter("name")+"</h1>");%>
<div>
    <div>
        <button onclick="location.href='/Testing_war/list'">Список студентов</button>
        <button onclick="location.href='/Testing_war/add'">Добавить студента</button>
        <button onclick="location.href='/Testing_war/delete'">Удалить студента</button>
        <button onclick="location.href='/Testing_war/createQuestion'">Создать вопрос</button>
        <button onclick="location.href='/Testing_war/listQuestions'">Список вопросов</button>
        <button onclick="location.href='/Testing_war/generateReport'">Создать отчет</button>
    </div>
</div>
</body>
</html>
