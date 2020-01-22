package service.xml.parsers;


import java.io.File;
import java.io.InputStream;


public interface IParser {
    void parse(File file);
    void parse(InputStream inputStream);
}
