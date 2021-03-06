package service.xml;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import data.question.Question;
import service.QuestionService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class QuestionXML implements IParserXML {

    @Override
    public File toXML(IXML ixml) {
        return null;
    }

    public File toXML(List<Question> questions) throws ParserConfigurationException {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("questions");
            for (Question myQuestion : questions) {
                Element question = document.createElement("question");
                Element questionText = document.createElement("questionText");
                questionText.setTextContent(myQuestion.getQuestion());
                Element trueNumber = document.createElement("trueNumber");
                trueNumber.setTextContent(myQuestion.getTrueNumber() + "");
                Element answer1 = document.createElement("answer1");
                answer1.setTextContent(myQuestion.getAnswers().get(0));
                Element answer2 = document.createElement("answer2");
                answer2.setTextContent(myQuestion.getAnswers().get(1));
                Element answer3 = document.createElement("answer3");
                answer3.setTextContent(myQuestion.getAnswers().get(2));
                Element answer4 = document.createElement("answer4");
                answer4.setTextContent(myQuestion.getAnswers().get(3));
                Element assessment = document.createElement("assessment");
                assessment.setTextContent(myQuestion.getAssessment() + "");
                question.appendChild(questionText);
                question.appendChild(trueNumber);
                question.appendChild(answer1);
                question.appendChild(answer2);
                question.appendChild(answer3);
                question.appendChild(answer4);
                question.appendChild(assessment);
                root.appendChild(question);
            }

            document.appendChild(root);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            Writer fstream;
            File file = new File( "questionXML.xml");
            fstream = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8);
            StreamResult result = new StreamResult(fstream);
            transformer.transform(source, result);
            fstream.close();
        } catch (TransformerException | IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<IXML> fromXML(File file) {
        List<IXML> listQuestions = new ArrayList<>();
        QuestionService questionService = new QuestionService();
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.parse(file);
            Node root = document.getDocumentElement();
            NodeList questions = root.getChildNodes();
            for (int i = 1; i < questions.getLength(); i++) {
                Node question = questions.item(i);
                NodeList questionProps = question.getChildNodes();
                Question myQuestion = new Question();
                int trueNumber = 0;
                for (int j = 0; j < questionProps.getLength(); j++) {
                    Node node = questionProps.item(j);
                    if (node.getNodeName().equals("questionText")) {
                        myQuestion.setQuestion(node.getTextContent());
                    }
                    if (node.getNodeName().equals("trueNumber")) {
                        trueNumber = Integer.parseInt(node.getTextContent());
                        System.out.println(trueNumber+" if ");
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
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return listQuestions;
    }
}


