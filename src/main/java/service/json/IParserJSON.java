package service.json;

import data.IJSON;

import java.io.File;

public interface IParserJSON {
     File toJSON(IJSON ijson);
     IJSON fromJSON(File file);
}
