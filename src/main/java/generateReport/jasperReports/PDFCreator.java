package generateReport.jasperReports;

import dao.spring.AnswerSpringDAO;
import generateReport.IReportCreator;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JsonDataSource;
import org.json.JSONObject;
import test.Context;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class PDFCreator implements IReportCreator {

    public void createReport(String idQuestion) {

        JSONObject jsonObject =  Context.getInstance()
                .getBean("answerSpringDAO", AnswerSpringDAO.class).selectStudentAnswers(idQuestion);
        String sourceFileName = "C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\src\\main\\resources\\fromJson.jasper";
        String printFileName = null;
        try {
            String myJson = jsonObject.toString();
            InputStream inputStream = new ByteArrayInputStream(myJson.getBytes(Charset.forName("UTF-8")));
            JsonDataSource jsonDataSource = new JsonDataSource(inputStream);
            Map parameters = new HashMap();
            printFileName = JasperFillManager.fillReportToFile(sourceFileName, parameters, jsonDataSource);
            if (printFileName != null) {
                JasperExportManager.exportReportToPdfFile(printFileName,
                        "C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\reports\\question360.pdf");
            }
        } catch (JRException e) {
            e.printStackTrace();
        }
    }
}


