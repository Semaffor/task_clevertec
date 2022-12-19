package by.bsuir.printer;

import by.bsuir.entity.Receipt;

import java.io.IOException;

public interface Printer {
    void printReceipt(String data) throws IOException;
}
