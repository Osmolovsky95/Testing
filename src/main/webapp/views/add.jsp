<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%request.setCharacterEncoding("UTF-8");%>
<html lang="ru">
<html>
<head>
    <title>Добавить студента</title>
</head>

<body>
<div>
    <h1>Новичок</h1>
</div>

<div>
    <%
        if (request.getAttribute("userName") != null) {
            out.println("<p>User '" + request.getAttribute("userName") + "' added!</p>");
        }
    %>
    <div>
        <div>
            <h2>Добавить студента</h2>
        </div>

        <form method="post">
            <label>Имя:
                <input type="text" name="name"><br />
            </label>
            <label>Пароль:
                <input type="password" name="pass"><br />
            </label>
            <button type="submit">Submit</button>
        </form>
    </div>
</div>

<div>
    <button onclick="location.href='/'">На главную</button>
</div>
</body>
</html>