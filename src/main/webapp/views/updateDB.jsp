<%@ page import="java.io.PrintWriter" %><%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 18.12.2019
  Time: 18:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>IT -academy students</title>
</head>
<body>
<!-- header -->
<div>
    <h1>IT - academy students</h1>
</div>
<% PrintWriter printWriter=response.getWriter();
printWriter.println("<h1>Добро пожаловать "+ request.getParameter("name")+"</h1>");%>
<div>       <!-- content -->
    <div>    <!-- buttons holder -->
        <button onclick="location.href='/Testing_war/list'">Список студентов</button>
        <button onclick="location.href='/Testing_war/add'">Добавить студента</button>
        <button onclick="location.href='/Testing_war/delete'">Удалить студента</button>
        <button onclick="location.href='/Testing_war/createQuestion'">Создать вопрос</button>
        <button onclick="location.href='/Testing_war/listQuestions'">Список вопросов</button>
    </div>
</div>
</body>
</html>
