package dao.spring;


import data.question.BankQuestions;
import data.question.Question;
import org.springframework.jdbc.core.JdbcTemplate;
import java.util.Map;

public class QuestionSpringDAO {
   private JdbcTemplate jdbcTemplate;

    public QuestionSpringDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void addQuestion(Question question)  {
        String insertQuestion="INSERT INTO questions (question,assessment) Values (?,?) RETURNING id";
           Map map= jdbcTemplate.queryForMap(insertQuestion,question.getQuestion(),question.getAssessment());
           question.setId((int)map.get("id"));
        BankQuestions.getInstance().getQuestions().add(question);
    }

    public void addQuestionAnswers(long question_id,long answer_id)  {
        String insertQuestion="INSERT INTO question_answers  (question_id,answer_id) Values (?,?)";
        jdbcTemplate.update(insertQuestion,question_id,answer_id);
    }
}
