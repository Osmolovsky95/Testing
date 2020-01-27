package test;

import dao.spring.AnswerSpringDAO;
import dao.spring.QuestionSpringDAO;
import data.question.Question;
import net.sf.jasperreports.engine.JRException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import java.io.*;
import java.util.*;

public class Delete {
    public static void main(String[] args) throws IOException, JRException {

    //  new LoaderQuestionsDAO().load();
             //String s=360+"";
        Question question=new Question();
        question.setQuestion("asdasdas");
        question.setTrueNumber(1);
        question.setAssessment(1);
        ArrayList arrayList=new ArrayList();
        arrayList.add("вставлен");
        arrayList.add("вставлен");
        question.setAnswers(arrayList);

        ApplicationContext applicationContext=new FileSystemXmlApplicationContext("src/main/resources/spring/context.xml");
       // JdbcTemplate jdbcTemplate=(JdbcTemplate) applicationContext.getBean("jdbcTemplate");


        QuestionSpringDAO questionSpringDAO=applicationContext.getBean("questionSpringDAO",QuestionSpringDAO.class);
        questionSpringDAO.addQuestion(question);


      // new ExcelCreator().createReport(s);

      ///  new PDFCreator().createReport(s);
      //  new PDFCreator().createReport(AnswerDAO.selectStudentAnswers(s));


        //new PDFCreator().createReport(s);
        //System.out.println(jsonObject);
    // JSONObject jsonObject1=jsonObject.getJSONObject("question");
    //    jsonObject1.get("goodAnswer");
     //   List answers=new ArrayList();
   //    JSONArray jsonArray = jsonObject1.getJSONArray("answers");
    //    System.out.println(jsonArray.toList().toString())
    }
}
