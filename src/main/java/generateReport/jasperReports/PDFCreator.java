package generateReport.jasperReports;

import dao.AnswerDAO;
import data.student.Student;
import generateReport.IReportCreator;
import generateReport.JsonParser;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.data.JsonDataSource;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.*;

/*Для получения отчета о вопросе создаем String s=360+"";
                                         new PDFCreator().createReport(s);
                                         где s-id вопроса на который ищем;
* */
public class PDFCreator implements IReportCreator {
    @Override
    public void createReport(Set<Student> students) {

    }

    // TODO: 20.01.2020 Сделать генерацию отчета по вопросу
 /*  public void createReport(String idQuestion) {
        long start=new Date().getTime();
        ReportBeanQuestion reportBeanQuestion = new JsonParser().parse(idQuestion);
        String sourceFileName ="src/main/resources/test2.jasper";
        String printFileName = null;

            List listt=new ArrayList();
            listt.add(reportBeanQuestion);

            JRBeanCollectionDataSource jrBeanCollectionDataSource=new JRBeanCollectionDataSource(listt);
            Map parameters = new HashMap();
            try {

             printFileName = JasperFillManager.fillReportToFile(sourceFileName, parameters, jrBeanCollectionDataSource);
                if (printFileName != null) {
                   JasperExportManager.exportReportToPdfFile(printFileName,
                            "reports/report.pdf");
                }
            } catch (JRException ex) {
                ex.printStackTrace();
            }
        long finish=new Date().getTime();
        System.out.println((finish-start)+"  from list");
    }*/

    public void createReport(String s){

        long start=new Date().getTime();
        JSONObject jsonObject= AnswerDAO.selectStudentAnswers(s);
        System.out.println(jsonObject.toString());
        String sourceFileName ="C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\src\\main\\resources\\fromJson.jasper";
        String printFileName = null;
       try {
           String myJson = jsonObject.toString();
           InputStream inputStream = new ByteArrayInputStream(myJson.getBytes());
           JsonDataSource jsonDataSource = new JsonDataSource(inputStream);
           Map parameters = new HashMap();


           printFileName = JasperFillManager.fillReportToFile(sourceFileName, parameters, jsonDataSource);
           if (printFileName != null) {
               JasperExportManager.exportReportToPdfFile(printFileName,
                       "reports/reportFromJson.pdf");
           }
       } catch (JRException ex) {
            ex.printStackTrace();
        }
       long finish=new Date().getTime();
        System.out.println((finish-start)+"  from json");
    }
    }


