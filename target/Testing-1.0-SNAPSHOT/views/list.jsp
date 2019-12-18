<%@ page import="java.sql.Connection" %>
<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.Statement" %>
<%@ page import="java.sql.ResultSet" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<html>
<head>
 <title>Users</title>
</head>

<body>
<div>
 <h1>Super app!</h1>
</div>
<form method="post" >
<div>
 <div>
  <div>
   <h2>Студенты</h2>
  </div>
  <%Class.forName("com.mysql.cj.jdbc.Driver");
   Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/novacom?serverTimezone=UTC", "root", "root");
   Statement statement = connection.createStatement();
   ResultSet resultSet=statement.executeQuery("select * from students");
   out.println("<select name=\"student\">");
   while (resultSet.next()){
    String name=resultSet.getString("name");
    out.println("<option>" + name + "</option>");
   }
   out.println("</select>");
  %>
 </div>
</div>
 <div>
 <button onclick="location.href='/Testing_war/list'">Выбрать</button>
</div>
</form>
<div>
 <button onclick="location.href='/Testing_war/'">На главную</button>
</div>
</body>
</html>