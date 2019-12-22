package Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    private String name;
    private static int id=1;
    private  List<Double> assessments=new ArrayList<>();
    private String password;

    public Student(String name,String password) {
        this.name=name;
        this.password=password;
        this.id=id++;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Double> getAssessments() {
        return assessments;
    }

    public void setAssessments(List<Double> assessments) {
        this.assessments = assessments;
    }

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
