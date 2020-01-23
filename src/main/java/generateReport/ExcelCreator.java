package generateReport;

import dao.AnswerDAO;
import data.student.Student;
import generateReport.jasperReports.ReportBeanQuestion;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.json.JSONObject;

import java.io.*;
import java.text.DecimalFormat;
import java.util.Map;
import java.util.Set;

public class ExcelCreator implements IReportCreator {

    @Override
    public void createReport(Set<Student> students) {
        Workbook workbook = new HSSFWorkbook();
        File file = new File("C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\reports", "Report.xls");
        try (OutputStream fileOut = new FileOutputStream(file)) {
            Sheet sheet1 = workbook.createSheet("NameGroup");
            int numberColumnName = 1;
            int resultAssessment = 7;

            Row head = sheet1.createRow(0);

            Row row = sheet1.createRow(1);
            Cell name = row.createCell(0);
            name.setCellValue("Имя");
            Cell assessment = row.createCell(1);
            assessment.setCellValue("Оценка");
            Cell cell3 = row.createCell(resultAssessment);
            cell3.setCellValue("Средний балл");
            sheet1.setColumnWidth(7, 5000);
            Header header = sheet1.getHeader();
            header.setCenter("Отчет  группа № ......");
            DecimalFormat df = new DecimalFormat("##.##");
            int count = 1;
            for (Student student : students) {
                int numberColumnAssessment = 1;
                Row row1 = sheet1.createRow(count);
                Cell cell = row1.createCell(numberColumnName);
                cell.setCellValue(student.getName());
                if (student.getAssessments() != null) {
                    for (int i = 0; i < student.getAssessments().size(); i++) {
                        Cell cell2 = row1.createCell(numberColumnAssessment++);
                        cell2.setCellValue(student.getAssessments().get(i));
                    }
                }

                Cell result = row1.createCell(resultAssessment);
                result.setCellValue(df.format(student.getResultAssessment()));
                count++;
            }
            workbook.write(fileOut);
        } catch (FileNotFoundException f) {
            System.out.println(f.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

    /*public void createReport(String s){
        System.out.println("excel");
        ReportBeanQuestion reportBeanQuestion=new JsonParser().parse(s);
        Workbook workbook=new HSSFWorkbook();
        File file=new File("C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\reports","Report.xls");

        try (OutputStream fileOut =  new FileOutputStream(file)) {
            Sheet sheet1 = workbook.createSheet ( "Question № "+reportBeanQuestion.getId());

            int resultAssessment=7;

            Row head=sheet1.createRow(0);
            Cell headCell=head.createCell(0);
            headCell.setCellValue(reportBeanQuestion.getText());
            //sheet1.addMergedRegion(new CellRangeAddress(0,0,0,5));
            sheet1.autoSizeColumn(0);
            Row row = sheet1.createRow (1);
            Cell id = row.createCell (0);
            id.setCellValue("id");
            Cell name = row.createCell (1);
            name.setCellValue("name");
            Cell answerFromStudent=row.createCell(2);
            answerFromStudent.setCellValue("answer");

            Header header=sheet1.getHeader();
            header.setCenter("Отчет по вопросу №  "+reportBeanQuestion.getId());
          //  int count=2;
          //  for(Map<String,Object> students:reportBeanQuestion.getStudents()){
           //     Row myRow=sheet1.createRow(count);
            //    myRow.createCell(0).setCellValue(Long.parseLong((String) students.get("id")));
            //    myRow.createCell(1).setCellValue((String) students.get("name"));
             //   myRow.createCell(2).setCellValue(Long.parseLong((String)students.get("answer")));
             //   count++;
           // }
           //     workbook.write (fileOut);
           // } catch (FileNotFoundException ex) {
          //  ex.printStackTrace();
      //  } catch (IOException ex) {
       //     ex.printStackTrace();
      //  }*/



