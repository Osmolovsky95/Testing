package service.xml.parsers;

import data.question.Question;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import service.QuestionService;
import service.xml.IXML;
import test.Context;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ParserDOM implements IParser {
    @Override
    public void parse(File file) {
        List<IXML> listQuestions = new ArrayList<>();
        QuestionService questionService = Context.getInstance().getBean("questionService",QuestionService.class);
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            Node root = document.getDocumentElement();
            NodeList questions = root.getChildNodes();
            for (int i = 1; i < questions.getLength(); i++) {
                Node question = questions.item(i);
                NodeList questionProps = question.getChildNodes();
                Question myQuestion = Context.getInstance().getBean("question",Question.class);
                int trueNumber = 0;
                for (int j = 0; j < questionProps.getLength(); j++) {
                    Node node = questionProps.item(j);
                    if (node.getNodeName().equals("questionText")) {
                        myQuestion.setQuestion(node.getTextContent());
                    }
                    if (node.getNodeName().equals("trueNumber")) {
                        trueNumber = Integer.parseInt(node.getTextContent());
                    }
                    if (node.getNodeName().equals("assessment")) {
                        myQuestion.setAssessment(Double.parseDouble(node.getTextContent()));
                    }
                    if (node.getNodeName().equals("answer")) {
                        myQuestion.getAnswers().add(node.getTextContent());
                    }
                }
                questionService.addQuestion(myQuestion, trueNumber);
                listQuestions.add(myQuestion);
            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }

    public void parse(InputStream inputStream){
        List<IXML> listQuestions = new ArrayList<>();
        QuestionService questionService =  Context.getInstance().getBean("questionService",QuestionService.class);
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(inputStream);
            Node root = document.getDocumentElement();
            NodeList questions = root.getChildNodes();
            for (int i = 1; i < questions.getLength(); i++) {
                Node question = questions.item(i);
                NodeList questionProps = question.getChildNodes();
                Question myQuestion = Context.getInstance().getBean("question",Question.class);
                int trueNumber = 0;
                for (int j = 0; j < questionProps.getLength(); j++) {
                    Node node = questionProps.item(j);
                    if (node.getNodeName().equals("questionText")) {
                        myQuestion.setQuestion(node.getTextContent());
                    }
                    if (node.getNodeName().equals("trueNumber")) {
                        trueNumber = Integer.parseInt(node.getTextContent());
                    }
                    if (node.getNodeName().equals("assessment")) {
                        myQuestion.setAssessment(Double.parseDouble(node.getTextContent()));
                    }
                    if (node.getNodeName().equals("answer")) {
                        myQuestion.getAnswers().add(node.getTextContent());
                    }
                }
                questionService.addQuestion(myQuestion, trueNumber);
                listQuestions.add(myQuestion);

            }
        } catch (ParserConfigurationException | IOException | SAXException e) {
            e.printStackTrace();
        }
    }
}


