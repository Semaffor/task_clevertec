package by.bsuir.printer;

import java.io.File;
import java.io.IOException;

public interface Printer {
    void printReceipt(String data) throws IOException;
    File getFolder();
    File generateFileToWrite(File folder);
}
