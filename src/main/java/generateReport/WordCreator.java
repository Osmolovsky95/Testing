package generateReport;
import data.student.Student;

import java.util.Set;

public class WordCreator implements IReportCreator {

    @Override
    public void createReport(Set<Student> students) {
        System.out.println("Создание word-отчета");
        /*XWPFDocument doc = new XWPFDocument();
        try(OutputStream os = new FileOutputStream("Javatpoint.doc")) {
            XWPFParagraph paragraph = doc.createParagraph();
            XWPFRun run = paragraph.createRun();
            run.setText("Hello, This is javatpoint. This paragraph is written "+
                    "by using XWPFParagrah.");
            doc.write(os);
        }catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }*/

    }
}
