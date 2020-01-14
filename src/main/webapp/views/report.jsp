<%--
  Created by IntelliJ IDEA.
  User: A.Asmalouski
  Date: 09.01.2020
  Time: 17:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.io.PrintWriter" %>
<%@ page import="data.student.Student" %>
<%@ page import="java.util.Set" %>
<%@ page import="java.text.DecimalFormat" %>
<%@ page import="generateReport.TypeReport" %>

<html>
<head>
    <title>generateReport</title>
</head>
<div>
    <h1>Создать отчет</h1>
</div>
<body>
<div>
<% Set<Student> students=(Set<Student>) request.getAttribute("students");
    PrintWriter pw = response.getWriter();
    if (students.isEmpty()) {
        pw.println("Not students");
    } else {
        pw.println("<table border=\"1\" width=\"50%\" cellpadding=\"5\">");
        pw.println("<tr>");
        pw.println("<th>Имя</th>");
        pw.println("<th>Средний балл</th>");
        pw.println("<tr>");
        for (Student student : students) {

            if (student.getAssessments().isEmpty()) {
                pw.println("<tr><td>" + student.getName() + "</td> <td>"+student.getResultAssessment()+"</td></tr>");
            } else {
                double result = 0;
                DecimalFormat df = new DecimalFormat("##.##");
                pw.println("<tr><td>" + student.getName() + "</td> <td>"+df.format(student.getResultAssessment())+"</td></tr>");


            }
        }
        pw.println("<table>");
    };%>
        <div>
            <form method="GET">
           <select name="report">
               <option>Word</option>
               <option>Excel</option>
               <option>PDF</option>
           </select>
            <button type="submit">Создать</button>
            </form>
        </div>
</div>
</body>
</html>
