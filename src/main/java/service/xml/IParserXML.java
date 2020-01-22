package service.xml;

import java.io.File;
import java.util.List;

public interface IParserXML {
    File toXML(IXML ixml);
    List<IXML> fromXML(File file);
}
