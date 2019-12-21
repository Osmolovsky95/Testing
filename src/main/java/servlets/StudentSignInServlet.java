package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Iterator;

import Data.GroupStudents;
import Data.Student;
import Question.BankQuestions;
import Question.Question;

public class StudentSignInServlet extends HttpServlet {

static{
    Student student=new Student("sasha","1995");
    GroupStudents.getInstance().getStudents().add(student);
        BankQuestions bankQuestions= BankQuestions.getInstance();
        for (int i=0;i<10;i++) {
            Question question1 = new Question();
            question1.getAnswers().add("answer1");
            question1.getAnswers().add("answer2");
            question1.getAnswers().add("answer3");
            question1.getAnswers().add("answer4");
            question1.setQuestion("question "+i);
            question1.setTrueNumber(1);
            question1.setAssessment(2);
            question1.setId(Question.count);
            bankQuestions.getQuestions().add(i,question1);
        }

}
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/studentPage.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
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
            } else {
                pw.println("Invalid input");
            }





        }
    }
}
