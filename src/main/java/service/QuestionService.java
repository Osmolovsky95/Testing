package service;

import dao.loaders.LoaderQuestionsDAO;
import dao.spring.AnswerSpringDAO;
import dao.spring.QuestionSpringDAO;
import data.question.BankQuestions;
import data.question.Question;
import test.Context;

import java.util.List;


public class QuestionService {

    public void addQuestion(Question question,int trueNumber) {

         QuestionSpringDAO questionSpringDAO= Context.getInstance().getBean("questionSpringDAO", QuestionSpringDAO.class);
         questionSpringDAO.addQuestion(question);
         Context.getInstance().getBean("answerSpringDAO", AnswerSpringDAO.class).addAnswer(question,trueNumber);
         for(long idAnswer:question.getIdAnswers()){
             questionSpringDAO.addQuestionAnswers(question.getId(),idAnswer);
        }
        BankQuestions.getInstance().getQuestions().add(question);
    }

    public List<Question> getQuestions(){
        new LoaderQuestionsDAO().load();
        return BankQuestions.getInstance().getQuestions();
    }
}
