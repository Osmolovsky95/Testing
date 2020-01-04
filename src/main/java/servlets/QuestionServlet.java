package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import Data.AnswerDAO;
import Data.QuestionDAO;
import Question.BankQuestions;
import Question.Question;

public class QuestionServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/createQuestion.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        BankQuestions bankQuestions= BankQuestions.getInstance();

        Question question1=new Question();
        String answr1=req.getParameter("answer1");
        String answr2=req.getParameter("answer2");
        String answr3=req.getParameter("answer3");
        String answr4=req.getParameter("answer4");
        question1.getAnswers().add(req.getParameter(answr1));
        question1.getAnswers().add(req.getParameter(answr2));
        question1.getAnswers().add(req.getParameter(answr3));
        question1.getAnswers().add(req.getParameter(answr4));
        String qst=req.getParameter("question");
        double asessment=(Double.parseDouble(req.getParameter("assessment")));

        try {
           long id_question=QuestionDAO.addQuestion(qst,asessment);
           long id_1_answer= AnswerDAO.addAnswer(answr1);
           long id_2_answer= AnswerDAO.addAnswer(answr2);
           long id_3_answer= AnswerDAO.addAnswer(answr3);
           long id_4_answer= AnswerDAO.addAnswer(answr4);



            QuestionDAO.addQuestionAnswers(id_question,id_1_answer);
            QuestionDAO.addQuestionAnswers(id_question,id_2_answer);
            QuestionDAO.addQuestionAnswers(id_question,id_3_answer);
            QuestionDAO.addQuestionAnswers(id_question,id_4_answer);
            switch (Integer.parseInt(req.getParameter("trueNumber"))){
                case 1:AnswerDAO.addTrueAnswer(id_1_answer,id_question);
                    break;
                case 2:AnswerDAO.addTrueAnswer(id_2_answer,id_question);
                    break;
                case 3:AnswerDAO.addTrueAnswer(id_3_answer,id_question);
                    break;
                case 4:AnswerDAO.addTrueAnswer(id_4_answer,id_question);
                    break;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        question1.setQuestion(req.getParameter("question"));
        question1.setTrueNumber(Integer.parseInt(req.getParameter("trueNumber")));
        System.out.println(req.getParameter("trueNumber"));
        question1.setAssessment(Double.parseDouble(req.getParameter("assessment")));
        bankQuestions.getQuestions().add(question1);
        PrintWriter pw=resp.getWriter();
        pw.println("Question is added");
    }
}

