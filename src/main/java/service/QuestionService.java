package service;

import dao.AnswerDAO;
import dao.QuestionDAO;
import dao.loaders.LoaderQuestionsDAO;
import question.BankQuestions;
import question.Question;
import service.json.QuestionJSON;

import java.io.File;
import java.util.List;


public class QuestionService {

    public void addQuestion(Question question,int trueNumber) {
         QuestionDAO.addQuestion(question);
         AnswerDAO.addAnswer(question,trueNumber);
         for(long idAnswer:question.getIdAnswers()){
            QuestionDAO.addQuestionAnswers(question.getId(),idAnswer);
        }
        BankQuestions.getInstance().getQuestions().add(question);
        // TODO: 14.01.2020 потом убрать
        File file=new QuestionJSON().toJSON(question);
    }

    public List<Question> getQuestions(){
        new LoaderQuestionsDAO().load();
        return BankQuestions.getInstance().getQuestions();
    }

}
