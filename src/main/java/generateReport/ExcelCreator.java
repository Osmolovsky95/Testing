package generateReport;

import data.Student;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import java.io.*;
import java.text.DecimalFormat;
import java.util.Set;

public class ExcelCreator implements IReportCreator {

    @Override
    public void createReport(Set<Student> students) {
        System.out.println("Создание EXCEL");
        Workbook workbook=new HSSFWorkbook();
        File file=new File("C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\reports","Report.xls");
        try (OutputStream fileOut =  new FileOutputStream(file)) {
            Sheet sheet1 = workbook.createSheet ( "NameGroup" );
            int numberColumnName=0;
            int resultAssessment=7;
            Row row = sheet1.createRow ( numberColumnName );
            Cell name = row.createCell ( numberColumnName );
            name.setCellValue("Имя");
            Cell assessment = row.createCell (1);
            assessment.setCellValue("Оценка");
            Cell cell3=row.createCell(resultAssessment);
            cell3.setCellValue("Средний балл");
            sheet1.setColumnWidth(7,5000);
            Header header=sheet1.getHeader();
            header.setCenter("Отчет  группа № ......");
            DecimalFormat df = new DecimalFormat("##.##");
             int count=1;
               for (Student student:students){
                   int numberColumnAssessment=1;
               Row row1=sheet1.createRow(count);
               Cell cell=row1.createCell(numberColumnName);
               cell.setCellValue(student.getName());
               if (student.getAssessments()!=null){
                   for (int i=0;i<student.getAssessments().size();i++) {
                       Cell cell2 = row1.createCell(numberColumnAssessment++);
                       cell2.setCellValue(student.getAssessments().get(i));
                   }
               }

               Cell result=row1.createCell(resultAssessment);
               result.setCellValue(df.format(student.getResultAssessment()));
               count++;
           }
            workbook.write (fileOut);
        } catch ( FileNotFoundException f) {
            System.out.println (f.getMessage ());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
