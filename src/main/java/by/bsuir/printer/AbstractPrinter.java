package by.bsuir.printer;

import by.bsuir.util.DateHandler;

import java.io.File;
import java.util.Date;

public abstract class AbstractPrinter implements Printer {

    public static final String DEFAULT_FOLDER_LOCATION = "/../receipts/";

    @Override
    public File getFolder() {
        String absLocation = new File("src").getAbsolutePath();
        File folder = new File(absLocation + DEFAULT_FOLDER_LOCATION);
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder;
    }

    @Override
    public File generateFileToWrite(File folder) {
        DateHandler dateHandler = DateHandler.createDefault();
        String fileName = "receipt_" + dateHandler.getFullTime(new Date()) + ".txt";
        return new File(folder.getAbsolutePath() + "/" + fileName);
    }
}
