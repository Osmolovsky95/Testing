<%@ page import="java.io.PrintWriter" %>
<%@ page import="Data.Student" %>
<%@ page import="java.util.ArrayList" %>
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
  <%
   ArrayList<Student> names= ( ArrayList<Student>)request.getAttribute("userNames");
   PrintWriter pw=response.getWriter();
   pw.println("<select>");
   for (Student s:names){
    pw.println( "<option>Student: " +s.getName() + "</option>");
   }
   pw.println("</select>");%>
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