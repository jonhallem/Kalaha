package Kalaha;

import java.io.IOException;

public interface ISaveHandler{

    void readSave(String filename, Game game) throws IOException;


    void writeSave(String filename, Game game) throws IOException;
    
}
