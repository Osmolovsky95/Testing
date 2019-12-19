package Question;

import java.util.ArrayList;
import java.util.List;

public class Question {

    public static int count=1;
    private   String question;
    private List <String> answers=new ArrayList<>();
    private   int trueNumber;
    private double assessment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    private int id;
    public Question(){
    count++;
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

    public int getTrueNumber() {
        return trueNumber;
    }

    public void setTrueNumber(int trueNumber) {
        this.trueNumber = trueNumber;
    }

    @Override
    public String toString() {
        return "Question{" +
                "question='" + question + '\'' +
                ", answers=" + answers +
                ", trueNumber=" + trueNumber +
                ", assessment=" + assessment +
                '}';

    }
}
