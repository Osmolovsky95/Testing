package test;

import dao.AnswerDAO;
import dao.loaders.LoaderQuestionsDAO;
import generateReport.ExcelCreator;
import generateReport.jasperReports.PDFCreator;
import net.sf.jasperreports.engine.JRException;
import java.io.*;
import java.util.*;

public class Delete {
    public static void main(String[] args) throws IOException, JRException {

      new LoaderQuestionsDAO().load();
             String s=360+"";
      // new ExcelCreator().createReport(s);
        new PDFCreator().createReport(s);
      //  new PDFCreator().createReport(AnswerDAO.selectStudentAnswers(s));


        //new PDFCreator().createReport(s);
        //System.out.println(jsonObject);
    // JSONObject jsonObject1=jsonObject.getJSONObject("question");
    //    jsonObject1.get("goodAnswer");
     //   List answers=new ArrayList();
   //    JSONArray jsonArray = jsonObject1.getJSONArray("answers");
    //    System.out.println(jsonArray.toList().toString());






    }
}
