package generateReport;

import data.Student;


import java.util.Set;

public interface IReportCreator {
    void createReport(Set<Student> students);
}
