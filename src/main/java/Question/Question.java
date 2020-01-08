package Question;

import java.util.ArrayList;
import java.util.List;

public class Question {


    public static int count=0;
    private long id;
    private   String question;
    private List <String> answers=new ArrayList<>();
    private long trueNumber;
    private double assessment;
    private List <Long> idAnswers=new ArrayList<>();

    public List<Long> getIdAnswers() {
        return idAnswers;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Question(String question, long id) {
        this.id = id;
        this.question = question;
    }

    public Question() {
    }

    public double getAssessment() {
        return assessment;
    }

    public void setAssessment(double assessment) {
        this.assessment = assessment;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public long getTrueNumber() {
        return trueNumber;
    }

    public void setTrueNumber(long trueNumber) {
        this.trueNumber = trueNumber;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", answers=" + answers +
                ", trueNumber=" + trueNumber +
                ", assessment=" + assessment +
                ", idAnswers=" + idAnswers.get(0)+"  " +idAnswers.get(1)+"  " +idAnswers.get(2)+"  " +idAnswers.get(3)+
                '}';
    }
}
