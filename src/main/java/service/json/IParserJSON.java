package service.json;

import java.io.File;

public interface IParserJSON {
     File toJSON(IJSON ijson);
     IJSON fromJSON(File file);
}
