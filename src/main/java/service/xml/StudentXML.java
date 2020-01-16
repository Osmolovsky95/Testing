package service.xml;

import data.student.Student;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import service.StudentService;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class StudentXML implements IParserXML {

    @Override
    public File toXML(IXML ixml) {
        Student myStudent=(Student) ixml;
       try {
           DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
           Document document = documentBuilder.newDocument();
           Element root=document.createElement("students");
           Element student=document.createElement("student");
           Element name=document.createElement("name");
           name.setTextContent(""+myStudent.getName());
           Element password=document.createElement("password");
           password.setTextContent(""+ myStudent.getPassword());
           student.appendChild(name);
           student.appendChild(password);
           root.appendChild(student);
           document.appendChild(root);
           TransformerFactory transformerFactory = TransformerFactory.newInstance();
           Transformer transformer = transformerFactory.newTransformer();
           DOMSource source = new DOMSource(document);
           FileWriter writer = new FileWriter(new File("C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\xml","student.xml"));
           StreamResult result = new StreamResult(writer);
           transformer.setOutputProperty(OutputKeys.INDENT, "yes");
           transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
           transformer.transform(source, result);
           writer.close();
       } catch (ParserConfigurationException | IOException | TransformerException e) {
           e.printStackTrace();
       }
        return null;
    }

    public File toXML(Set<Student> students) {
        try {
            DocumentBuilder documentBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = documentBuilder.newDocument();
            Element root = document.createElement("students");

            for (Student student1:students){
                Element student=document.createElement("student");
                Element name=document.createElement("name");
                Element password=document.createElement("password");
                name.setTextContent(student1.getName());
                password.setTextContent(student1.getPassword());
                student.appendChild(name);
                student.appendChild(password);
                root.appendChild(student);
            }
            document.appendChild(root);
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(document);
            FileWriter writer = new FileWriter(new File("C:\\Users\\A.Asmalouski\\IdeaProjects\\Testing\\xml","students.xml"));
            StreamResult result = new StreamResult(writer);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "5");
            transformer.transform(source, result);
            writer.close();
        } catch (ParserConfigurationException | IOException | TransformerException e) {
            e.printStackTrace();
        }
        return null;
    }

        @Override
    public List<IXML> fromXML(File file) {
        List listStudents=new ArrayList<>();
        StudentService studentService=new StudentService();
        try {
            DocumentBuilder documentBuilder= DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document=documentBuilder.parse(file);
            Node root=document.getDocumentElement();
            NodeList questions=root.getChildNodes();
            for(int i=0;i<questions.getLength();i++){
                Node question=questions.item(i);
                NodeList questionProps = question.getChildNodes();
                Student student;
                String name=null;
                String password=null;
                for(int j=0;j<questionProps.getLength();j++){
                    Node node=questionProps.item(j);
                    if(node.getNodeName().equals("name")){
                       name=node.getTextContent();
                    }
                    if(node.getNodeName().equals("password")){
                        password=node.getTextContent();
                    }
                }
                student=new Student(name,password);
                studentService.addStudent(student);
            }
        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
        return listStudents;
    }
    }

