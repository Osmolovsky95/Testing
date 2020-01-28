package service;

import generateReport.IReportCreator;
import servlets.json.Utils;
import java.io.IOException;
import java.io.InputStream;


public class GenerateReportService {
    public void generateReport(IReportCreator iReportCreator, InputStream inputStream) throws IOException {
        StringBuffer stringBuffer= Utils.streamToString(inputStream);
        iReportCreator.createReport(stringBuffer.toString());
    }
}
