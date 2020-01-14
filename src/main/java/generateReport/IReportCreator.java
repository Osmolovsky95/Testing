package generateReport;

import data.student.Student;


import java.util.Set;

public interface IReportCreator {
    void createReport(Set<Student> students);
}
