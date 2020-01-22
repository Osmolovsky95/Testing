package service.xml.parsers;


import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ParserSAX implements IParser {

    @Override
    public void parse(File file) {
        try {
            SAXParser saxParser = this.getSAXParser();
            saxParser.parse(file, new SaxHandler());
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    public void parse(InputStream inputStream) {
        try {
            SAXParser saxParser = this.getSAXParser();
            saxParser.parse(inputStream, new SaxHandler());
        } catch (SAXException | IOException e) {
            e.printStackTrace();
        }
    }

    private SAXParser getSAXParser(){
        SAXParser saxParser=null;
        try {
            SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
            saxParser = saxParserFactory.newSAXParser();
        } catch (ParserConfigurationException | SAXException e) {
            e.printStackTrace();
        }
        return saxParser;
    }
    }



