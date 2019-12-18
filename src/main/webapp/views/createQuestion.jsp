<%--
  Created by IntelliJ IDEA.
  User: Alexander
  Date: 18.12.2019
  Time: 19:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Создайте вопрос</title>
</head>
<body>
<form method="post">
    <label>Вопрос:
        <input type="text" name="question"><br />
    </label>
    <label>Вариант ответа 1:
        <input type="text" name="answer1"><br />
    </label>
    <label>Вариант ответа 2:
        <input type="text" name="answer2"><br />
    </label>
    <label>Вариант ответа 3:
        <input type="text" name="answer3"><br />
    </label>
    <label>Вариант ответа 4:
    <input type="text" name="answer4"><br />
</label>
    <label>Номер правильного варианта ответа:
        <input type="text" name="trueNumber"><br />
    </label>
    <label>Количество баллов за правильный ответ:
        <input type="text" name="assessment"><br />
    </label>
    <button type="submit">Добавить</button>
</form>
</body>
</html>
