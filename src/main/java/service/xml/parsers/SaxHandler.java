package service.xml.parsers;

import data.question.Question;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import service.QuestionService;

import java.util.ArrayList;
import java.util.List;

public class SaxHandler extends DefaultHandler {

    String currentElement;
    List<Question> list = new ArrayList<>();
    Question question;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        currentElement = qName;
        if (currentElement.equals("question")) {
            question = new Question();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("question")){
            new QuestionService().addQuestion(question,(int)question.getTrueNumber());
            list.add(question);
        }
    }

    @Override
    public void characters(char[] ch, int start, int length) {
        if (currentElement.equals("questionText")) {
            question.setQuestion(new String(ch, start, length));
        }
        if (currentElement.equals("trueNumber")) {
            question.setTrueNumber(Integer.parseInt(new String(ch, start, length)));
        }
        if (currentElement.equals("answer")) {
            question.getAnswers().add(new String(ch,start,length));
        }
        if (currentElement.equals("assessment")) {
            question.setAssessment(Double.parseDouble(new String(ch,start,length)));
        }
    }
}
