package by.bsuir.reader;

import by.bsuir.exception.NotFoundException;
import by.bsuir.util.FileManager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class BasicReader implements Readable{

    protected final String DEFAULT_RESOURCES_PATH = "/main/resources/";
    protected String fileName;

    public BasicReader(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public String readFile() {
        File resources = FileManager.getProjectFolder(DEFAULT_RESOURCES_PATH);
        try (BufferedReader br = new BufferedReader(new FileReader(resources + "/" + fileName))){
            return br.readLine();
        } catch (IOException e) {
            throw new NotFoundException("File in resources folder not found: " + fileName);
        }
    }

    @Override
    public void setFileName(String path) {
        this.fileName = path;
    }
}
