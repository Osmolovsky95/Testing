package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Iterator;
import Data.AnswerDAO;
import Data.GroupStudents;
import Data.Student;
import Question.BankQuestions;
import Question.Question;

public class StudentSignInServlet extends HttpServlet {

static {
    Student student = new Student("1", "1", 1000);
    GroupStudents.getInstance().getStudents().add(student);

    String url = "jdbc:postgresql://localhost:5432/Testing";
    String name = "postgres";
    String password = "postgres";
    String sql = "select * from questions";
    ResultSet resultSet = null;

    try {
        Class.forName("org.postgresql.Driver");
        Connection connection = null;
        connection = DriverManager.getConnection(url, name, password);
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        resultSet = preparedStatement.executeQuery();

        while (resultSet.next()){
            int count=0;
           String questionText= resultSet.getString("question");
           long id_question=resultSet.getLong("id");
           double assessment=resultSet.getDouble("assessment");
          //Ответы
           ResultSet answers=AnswerDAO.getAnswers(id_question);
           Question question=new Question(questionText,id_question);
           question.setAssessment(assessment);
           while (answers.next()){
               String answer=answers.getString("answer");
               question.getAnswers().add(answer);
            }
       //    String sqlTrueAnswer="select * from trueAnswer where id_question=?";
          // PreparedStatement preparedStatement1=connection.prepareStatement(sqlTrueAnswer);
         //  preparedStatement1.setLong(1,id_question);
         //  ResultSet trueAnswer=preparedStatement1.executeQuery();
        //   long trueAnswer_id=0;
        //   while (trueAnswer.next()){
       //        trueAnswer_id=trueAnswer.getLong("id_answer");
        //   }
         //   System.out.println(trueAnswer_id);
            BankQuestions.getInstance().getQuestions().add(count,question);
            count++;
        }
    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
    RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/studentPage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String name = req.getParameter("name");
        String password = req.getParameter("pass");
        GroupStudents groupStudents = GroupStudents.getInstance();
        Iterator<Student> iterator = groupStudents.getStudents().iterator();
        Student student;
        PrintWriter pw = resp.getWriter();

        while (iterator.hasNext()) {
            student = iterator.next();
            if (student.getName().equals(name) & student.getPassword().equals(password)) {
               resp.sendRedirect("TestServlet");
                HttpSession session=req.getSession();
                session.setAttribute("currentStudent",student);
            } else {
                pw.println("Invalid input");
            }
        }







    }

}

