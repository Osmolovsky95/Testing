<%@ page import="java.io.PrintWriter" %>
<%@ page import="data.Student" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<html>
<head>
 <title>Студенты</title>
</head>
<body>
<form method="post" >

 <div>
  <h2>Студенты</h2>
  </div>
 <div>
  <%
   ArrayList<Student> names= ( ArrayList<Student>)request.getAttribute("userNames");
   PrintWriter pw=response.getWriter();
   pw.println("<select>");
   for (Student s:names){
    pw.println( "<option> " +s.getName() + "</option>");
   }
   pw.println("</select>");%>
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