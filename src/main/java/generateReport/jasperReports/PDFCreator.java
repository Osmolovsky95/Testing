package generateReport.jasperReports;

import data.student.Student;
import generateReport.IReportCreator;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
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
    public void createReport(String idQuestion) {

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
    }
    }


