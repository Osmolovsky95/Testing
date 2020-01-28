package generateReport;
import dao.jdbc.AnswerDAO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JsonDataSource;
import net.sf.jasperreports.engine.export.ooxml.JRDocxExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.json.JSONObject;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;


public class WordCreator implements IReportCreator {

    public void createReport(String idQuestion){

        JSONObject jsonObject= AnswerDAO.selectStudentAnswers(idQuestion);
        String sourceFileName ="C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\src\\main\\resources\\fromJson.jasper";
        try {
            String myJson = jsonObject.toString();
            InputStream inputStream = new ByteArrayInputStream(myJson.getBytes(Charset.forName("UTF-8")));
            JsonDataSource jsonDataSource = new JsonDataSource(inputStream);
            Map parameters = new HashMap();

            JasperPrint jasperPrint= JasperFillManager.fillReport(sourceFileName, parameters, jsonDataSource);
            if (jasperPrint != null) {

                JRDocxExporter exporter=new JRDocxExporter();
                exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
                File exporterReport=new File("C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\reports\\question360.docx");
                exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(exporterReport));
                exporter.exportReport();
            }
        } catch (JRException ex) {
            ex.printStackTrace();
        }
    }
}
