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
import Data.QuestionDAO;
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
            BankQuestions.getInstance().getQuestions().add(count,question);
            count++;
        }


    } catch (SQLException | ClassNotFoundException e) {
        e.printStackTrace();
    }
}
        /*for (int i=0;i<10;i++) {
            Question question1 = new Question();
            question1.getAnswers().add("answer1");
            question1.getAnswers().add("answer2");
            question1.getAnswers().add("answer3");
            question1.getAnswers().add("answer4");
            question1.setQuestion("question "+i);
            question1.setTrueNumber(1);
            question1.setAssessment(2);
            question1.setId(Question.count);
         //  BankQuestions.getQuestions().add(i,question1);
        }*/


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

