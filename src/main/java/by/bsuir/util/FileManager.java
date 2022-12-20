package by.bsuir.util;

import java.io.File;

public class FileManager {
    static public File getProjectFolder(String folderLocation) {
        String absLocation = new File("src").getAbsolutePath();
        File folder = new File(absLocation + folderLocation);
        if (!folder.exists()) {
            folder.mkdir();
        }
        return folder;
    }
}
