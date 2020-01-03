package servlets;

import Data.AnswerDAO;
import Data.QuestionDAO;
import Data.Student;
import Question.Question;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.ResultSet;
import java.sql.SQLException;

import Question.BankQuestions;
public class TestServlet extends HttpServlet {







     int countQuestion = -1;
     private   Student student;
     private double currentAssesment=0;
    // TODO: 21.12.2019  
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setAttribute("nameButton", "Ответить");
        HttpSession session=req.getSession();
        student=(Student) session.getAttribute("currentStudent");
        countQuestion++;
        Question question = BankQuestions.getInstance().getQuestions().get(countQuestion);
        req.setAttribute("question", question.getQuestion());
        req.setAttribute("answer0", question.getAnswers().get(0));
        req.setAttribute("answer1", question.getAnswers().get(1));
        req.setAttribute("answer2", question.getAnswers().get(2));
        req.setAttribute("answer3", question.getAnswers().get(3));
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/Test.jsp");
        requestDispatcher.forward(req, resp);

        int trueNumber = BankQuestions.getInstance().getQuestions().get(countQuestion).getTrueNumber();
        String[] s = req.getParameterValues("" + trueNumber);
        for (int i = 0; i < s.length; i++) {
            if (Integer.parseInt(s[i]) == trueNumber) {
                System.out.println("Верно");
                currentAssesment=currentAssesment+question.getAssessment();
                student.getAssessments().add(currentAssesment);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
            if(countQuestion<BankQuestions.getInstance().getQuestions().size()-1) {
                req.setAttribute("nameButton", "Ответить");
                countQuestion++;
                Question question = BankQuestions.getInstance().getQuestions().get(countQuestion);
                req.setAttribute("question", question.getQuestion());
                req.setAttribute("answer0", question.getAnswers().get(0));
                req.setAttribute("answer1", question.getAnswers().get(1));
                req.setAttribute("answer2", question.getAnswers().get(2));
                req.setAttribute("answer3", question.getAnswers().get(3));
                RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/Test.jsp");
                requestDispatcher.forward(req, resp);

                int trueNumber = BankQuestions.getInstance().getQuestions().get(countQuestion).getTrueNumber();
                String[] s = req.getParameterValues("" + trueNumber);
                for (int i = 0; i < s.length; i++) {
                    if (Integer.parseInt(s[i]) == trueNumber) {
                        System.out.println("Верно");
                       currentAssesment=currentAssesment+question.getAssessment();
                    }
                }
            }
            else{
                Question lastQuestion=BankQuestions.getInstance().getQuestions().get(BankQuestions.getInstance().getQuestions().size()-1);
                currentAssesment=currentAssesment+lastQuestion.getAssessment();
                PrintWriter pw=resp.getWriter();
                pw.println("Your assessment is  "+currentAssesment);
                student.getAssessments().add(currentAssesment);
            }


        try {
            ResultSet questions = QuestionDAO.getQuestions();
            while (questions.next()){
                String questionText=questions.getString("question");
                long id=questions.getLong("id");
                System.out.println(id+ "id from bd");
                Question question=new Question(questionText,id);

                ResultSet answers= AnswerDAO.getAnswers(id);
                while (answers.next()){
                    String s=answers.getString("answer");
                    System.out.println(s+ "ответ из бд");
                    question.getAnswers().add(s);
                }
                BankQuestions.getInstance().getQuestions().add(question);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        System.out.println(BankQuestions.getInstance().getQuestions().size()+ "  размер");

    }
}
