package Question;

import java.util.ArrayList;
import java.util.List;

public class BankQuestions {
     private List<Question> questions=new ArrayList<>();
    private static volatile BankQuestions instance;

    public static BankQuestions getInstance() {
        BankQuestions localInstance = instance;
        if (localInstance == null) {
            synchronized (BankQuestions.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BankQuestions();
                }
            }
        }
        return localInstance;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    public static String getQuestionForm(BankQuestions bankQuestions){
        String responseForm="";
        String responce1="<html><head><title>Test</title></head><body>"+"<form method=\"post\">"+ "<p><b>"+bankQuestions.getQuestions().get(0).getQuestion()+"</b></p>";
        String responce2="<p><input type= \"checkbox\" name=\"option1\" value=\"a1\">"+bankQuestions.getQuestions().get(0).getAnswers().get(0)+"<Br></p>";
        String responce3="<p><input type= \"checkbox\" name=\"option2\" value=\"a2\">"+bankQuestions.getQuestions().get(0).getAnswers().get(1)+"<Br></p>";
        String responce4="<p><input type= \"checkbox\" name=\"option3\" value=\"a3\">"+bankQuestions.getQuestions().get(0).getAnswers().get(2)+"<Br></p>";
        String responce5="<p><input type= \"checkbox\" name=\"option4\" value=\"a4\">"+bankQuestions.getQuestions().get(0).getAnswers().get(3)+"<Br></p>";
        String responce6="</form></body></html>";

        responseForm=responce1+responce2+responce3+responce4+responce5+responce6;
        return responseForm;
    }
}
