package by.bsuir.printer;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class PrinterTxt extends AbstractPrinter {

    @Override
    public void printReceipt(String data) throws IOException {
        File fileToWrite = generateFileToWrite(getFolder());

        try (FileWriter fileWriter = new FileWriter(fileToWrite)) {
            fileWriter.write(data);
            System.out.println("Чек распечатан");
        }
    }
}
