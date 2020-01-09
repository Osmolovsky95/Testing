package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student {
    private String name;
    private long id;
    private  List<Double> assessments=new ArrayList<>();
    private String password;

    public Student(String name, String password, long id) {
        this.name = name;
        this.id = id;
        this.password = password;
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

    public long getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
}
