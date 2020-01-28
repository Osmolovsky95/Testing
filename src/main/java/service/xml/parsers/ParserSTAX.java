package service.xml.parsers;

import data.question.Question;
import service.QuestionService;
import test.Context;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import javax.xml.stream.events.XMLEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ParserSTAX implements IParser {

    private Question question;
    private List<Question> questions=new ArrayList<>();

    @Override
    public void parse(File file) {
        try {
            InputStream inputStream=new FileInputStream(file);
            XMLInputFactory xmlInputFactory=XMLInputFactory.newFactory();
            XMLStreamReader xmlStreamReader=xmlInputFactory.createXMLStreamReader(inputStream);
             this.startParse(xmlStreamReader,inputStream);
        } catch (IOException | XMLStreamException e) {
            e.printStackTrace();
        }
    }

    public void parse (InputStream inputStream){
        try {
            XMLInputFactory xmlInputFactory=XMLInputFactory.newFactory();
            XMLStreamReader xmlStreamReader=xmlInputFactory.createXMLStreamReader(inputStream);
            this.startParse(xmlStreamReader,inputStream);
        } catch (XMLStreamException | IOException ex) {
            ex.printStackTrace();
        }
    }

    public void startParse(XMLStreamReader xmlStreamReader,InputStream inputStream) throws XMLStreamException, IOException {

        while (xmlStreamReader.hasNext()){
            int event = xmlStreamReader.next();
            if (event == XMLEvent.START_ELEMENT && xmlStreamReader.getLocalName().equals("question")) {
                question = Context.getInstance().getBean("question",Question.class);
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
        inputStream.close();
        xmlStreamReader.close();
    }
}

