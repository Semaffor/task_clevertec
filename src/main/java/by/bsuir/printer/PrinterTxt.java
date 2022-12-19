package by.bsuir.printer;

import by.bsuir.util.DateHandler;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Date;
import java.util.Objects;

//TODO SIMPLIFY
public class PrinterTxt implements Printer {

    @Override
    public void printReceipt(String data) throws IOException {
        String absLocation = new File("src").getAbsolutePath();
        File folder = new File(absLocation + "/../receipts/");
        if (!folder.exists()) {
            folder.mkdir();
        }

        DateHandler dateHandler = DateHandler.createDefault();
        String fileName = "receipt_" + dateHandler.getFullTime(new Date()) + ".txt";
        File fileToWrite = new File(folder.getAbsolutePath() + "/" + fileName);

        try (FileWriter fileWriter = new FileWriter(fileToWrite)) {
            fileWriter.write(data);
        }
    }
}
