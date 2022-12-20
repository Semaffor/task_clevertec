package by.bsuir.printer;

import by.bsuir.util.DateHandler;
import by.bsuir.util.FileManager;

import java.io.File;
import java.util.Date;

public abstract class AbstractPrinter implements Printer {

    public static final String DEFAULT_FOLDER_LOCATION = "/../receipts/";

    @Override
    public File getFolder() {
        return FileManager.getProjectFolder(DEFAULT_FOLDER_LOCATION);
    }

    @Override
    public File generateFileToWrite(File folder) {
        DateHandler dateHandler = DateHandler.createDefault();
        String fileName = "receipt_" + dateHandler.getFullTime(new Date()) + ".txt";
        return new File(folder.getAbsolutePath() + "/" + fileName);
    }
}
