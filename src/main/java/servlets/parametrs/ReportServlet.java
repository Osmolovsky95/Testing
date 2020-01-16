package servlets.parametrs;

import data.student.GroupStudents;
import data.student.Student;
import generateReport.ExcelCreator;
import generateReport.IReportCreator;
import generateReport.PDFCreator;
import generateReport.WordCreator;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Set;

public class ReportServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        PrintWriter pw = resp.getWriter();
        Set<Student> students = GroupStudents.getInstance().getStudents();
        req.setAttribute("students",students);
        if(req.getParameter("report")!=null) {
            switch (req.getParameter("report")) {
                case "Word":
                    IReportCreator wordCreator = new WordCreator();
                    wordCreator.createReport(students);
                    break;
                case "Excel":
                     IReportCreator excelCreator = new ExcelCreator();
                     excelCreator.createReport(students);
                    break;
                case "PDF":
                    IReportCreator pdfCreator = new PDFCreator();
                    pdfCreator.createReport(students);
                    break;
            }
        }
        RequestDispatcher requestDispatcher=req.getRequestDispatcher("views/report.jsp");
        requestDispatcher.forward(req,resp);
    }
}
