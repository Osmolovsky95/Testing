<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Главная</title>
</head>
<body>
<div>
    <h1>Добро пожаловать на тест!</h1>
</div>

<div>
    <div>
        <button onclick="location.href='/Testing_war/studentPage'">Войти как студент</button>
        <button onclick="location.href='/Testing_war/administratorPage'">Войти как администратор</button>
    </div>
</div>
</body>
</html>