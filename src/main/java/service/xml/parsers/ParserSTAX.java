package service.xml.parsers;

import data.question.Question;
import service.QuestionService;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParserSTAX implements IParser {
    Question question;
    List<Question> questions=new ArrayList<>();

    @Override
    public void parse(File file) {
        try {
            InputStream inputStream=new FileInputStream(file);
            XMLInputFactory xmlInputFactory=XMLInputFactory.newFactory();
            XMLStreamReader xmlStreamReader=xmlInputFactory.createXMLStreamReader(inputStream);
             while (xmlStreamReader.hasNext()){
                 int event = xmlStreamReader.next();
                 if (event == XMLEvent.START_ELEMENT && xmlStreamReader.getLocalName().equals("question")) {
                   question=new Question();
                 }
                     if (event == XMLEvent.START_ELEMENT && xmlStreamReader.getLocalName().equals("questionText")) {
                         question.setQuestion(xmlStreamReader.getElementText());
                     }
                 if (event == XMLEvent.START_ELEMENT && xmlStreamReader.getLocalName().equals("assessment")) {
                     question.setAssessment(Double.parseDouble(xmlStreamReader.getElementText()));
                 }
                 if (event == XMLEvent.START_ELEMENT && xmlStreamReader.getLocalName().equals("answer")) {
                     question.getAnswers().add(xmlStreamReader.getElementText());
                 }
                 if (event == XMLEvent.START_ELEMENT && xmlStreamReader.getLocalName().equals("trueNumber")) {
                     question.setTrueNumber(Long.parseLong(xmlStreamReader.getElementText()));
                 }
                 if (event == XMLEvent.END_ELEMENT && xmlStreamReader.getLocalName().equals("question")) {
                     new QuestionService().addQuestion(question,(int)question.getTrueNumber());
                     questions.add(question);
                     System.out.println(question);
                 }
                 }
            } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
    }
    }

