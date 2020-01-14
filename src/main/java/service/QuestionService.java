package service;

import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.loaders.LoaderQuestionsDAO;
import data.question.BankQuestions;
import data.question.Question;
import java.util.List;


public class QuestionService {

    public void addQuestion(Question question,int trueNumber) {
         QuestionDAO.addQuestion(question);
         AnswerDAO.addAnswer(question,trueNumber);
         for(long idAnswer:question.getIdAnswers()){
            QuestionDAO.addQuestionAnswers(question.getId(),idAnswer);
        }
        BankQuestions.getInstance().getQuestions().add(question);
    }

    public List<Question> getQuestions(){
        new LoaderQuestionsDAO().load();
        return BankQuestions.getInstance().getQuestions();
    }

}
