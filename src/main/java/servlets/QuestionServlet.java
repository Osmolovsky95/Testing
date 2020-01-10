package servlets;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.AnswerDAO;
import dao.QuestionDAO;
import question.BankQuestions;
import question.Question;


public class QuestionServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("views/createQuestion.jsp");
        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setCharacterEncoding("UTF-8");
        req.setCharacterEncoding("UTF-8");
        BankQuestions bankQuestions= BankQuestions.getInstance();
        String answer1=req.getParameter("answer1");
        String answer2=req.getParameter("answer2");
        String answer3=req.getParameter("answer3");
        String answer4=req.getParameter("answer4");
        String qst=req.getParameter("question");

        List <String> answers=new ArrayList<>();
        answers.add(answer1);
        answers.add(answer2);
        answers.add(answer3);
        answers.add(answer4);

        double assessment=(Double.parseDouble(req.getParameter("assessment")));

        try {
            Question question = QuestionDAO.addQuestion(qst, assessment);
            QuestionDAO.addAnswersToQuestion(question,answers);

            long id_question = question.getId();
            long id_1_answer = AnswerDAO.addAnswer(answer1);
            long id_2_answer = AnswerDAO.addAnswer(answer2);
            long id_3_answer = AnswerDAO.addAnswer(answer3);
            long id_4_answer = AnswerDAO.addAnswer(answer4);

            question.getIdAnswers().add(id_1_answer);
            question.getIdAnswers().add(id_2_answer);
            question.getIdAnswers().add(id_3_answer);
            question.getIdAnswers().add(id_4_answer);

            switch (Integer.parseInt(req.getParameter("trueNumber"))) {
                case 1:
                    AnswerDAO.addTrueAnswer(id_1_answer, id_question);
                    question.setTrueNumber(id_1_answer);
                    break;
                case 2:
                    AnswerDAO.addTrueAnswer(id_2_answer, id_question);
                    question.setTrueNumber(id_2_answer);
                    break;
                case 3:
                     AnswerDAO.addTrueAnswer(id_3_answer, id_question);
                     question.setTrueNumber(id_3_answer);
                    break;
                case 4:
                     AnswerDAO.addTrueAnswer(id_4_answer, id_question);
                    question.setTrueNumber(id_4_answer);
                    break;
            }

            question.setQuestion(req.getParameter("question"));
            question.setAssessment(assessment);
            bankQuestions.getQuestions().add(question);
            QuestionDAO.addQuestionAnswers(id_question, id_1_answer);
            QuestionDAO.addQuestionAnswers(id_question, id_2_answer);
            QuestionDAO.addQuestionAnswers(id_question, id_3_answer);
            QuestionDAO.addQuestionAnswers(id_question, id_4_answer);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        PrintWriter pw=resp.getWriter();
        pw.println("Question is added");

    }
}

