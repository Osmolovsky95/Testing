package servlets.parametrs;

import generateReport.IReportCreator;
import generateReport.WordCreator;
import service.GenerateReportService;
import test.Context;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;



public class ReportServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setContentType("text/html;charset=UTF-8");
        req.getInputStream();

        GenerateReportService generateReportService= Context.getInstance().getBean("generateReportService",GenerateReportService.class);
        IReportCreator wordCreator=Context.getInstance().getBean("wordCreator",WordCreator.class);
        generateReportService.generateReport(wordCreator,req.getInputStream());

      //  RequestDispatcher requestDispatcher=req.getRequestDispatcher("views/report.jsp");
       // requestDispatcher.forward(req,resp);
    }
}
