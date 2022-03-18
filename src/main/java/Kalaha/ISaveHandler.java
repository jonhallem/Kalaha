package Kalaha;

import java.io.FileNotFoundException;

public interface ISaveHandler{

    Game readSave(String filename, Game game) throws FileNotFoundException;


    void writeSave(String filename, Game game) throws FileNotFoundException;
    
}
