package data;

public class AssessmentSetter {
    private Student student;
    private double assessment;

    public AssessmentSetter(Student student, double assessment) {
        student.getAssessments().add(assessment);
    }
}
