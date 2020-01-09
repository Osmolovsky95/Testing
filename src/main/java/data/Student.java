package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Student implements IPerson {
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id &&
                Objects.equals(name, student.name) &&
                Objects.equals(assessments, student.assessments) &&
                Objects.equals(password, student.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, id, assessments, password);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", password='" + password + '\'' +
                '}';
    }

    public long getId() {
        return id;
    }
}
