package by.bsuir.printer;

import by.bsuir.util.DateHandler;

public class PrinterFactory {

    public static Printer getReceiptPrinter(PrinterType type) {
        return switch (type) {
            case PDF -> new PrinterPdf();
            case TXT -> new PrinterTxt();
        };
    }
}
