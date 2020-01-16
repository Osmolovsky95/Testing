package service.xml.parsers;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

public class ParseHelper {
    IParser iParser;
    File file;

    public ParseHelper(IParser iParser, File file)  {
     this.iParser=iParser;
     this.file=file;
     iParser.parse(file);
    }
}
