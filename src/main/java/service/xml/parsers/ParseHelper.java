package service.xml.parsers;


import java.io.File;
import java.io.InputStream;


public class ParseHelper {

    public ParseHelper(IParser iParser, File file)  {
        iParser.parse(file);
    }

    public ParseHelper(IParser iParser, InputStream inputStream)  {
        iParser.parse(inputStream);
    }
}
