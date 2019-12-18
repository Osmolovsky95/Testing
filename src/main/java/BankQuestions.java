import java.util.ArrayList;
import java.util.List;

public class BankQuestions {
     private List<Question> questions=new ArrayList<>();
     private static BankQuestions instance = new BankQuestions();

    public static BankQuestions getInstance() {
        return instance;
    }

    public List<Question> getQuestions() {
        return questions;
    }
}
