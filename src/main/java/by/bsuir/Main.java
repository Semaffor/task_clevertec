package by.bsuir;

import by.bsuir.entity.Receipt;
import by.bsuir.service.*;
import by.bsuir.service.impl.MenuServiceImpl;
import by.bsuir.service.impl.OrderServiceImpl;
import by.bsuir.service.impl.ReceiptServiceImpl;
import by.bsuir.util.*;
import by.bsuir.printer.Printer;
import by.bsuir.printer.PrinterFactory;
import by.bsuir.printer.PrinterType;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;

public class Main {

    //better to set into properties
    public static final Locale locale= new Locale("ru", "RU");

    static String[] argGlob = {"1-2", "3-4", "5-6", "3-1", "2-1", "card-1234"};

    public static void main(String[] args) {

        MenuService menuService = new MenuServiceImpl(new ProductGenerator(new Random()));
        ReceiptService receiptService = new ReceiptServiceImpl();
        OrderServiceImpl orderService = new OrderServiceImpl();

        ArgsParser argsParser = new ArgsParser(menuService, receiptService, orderService);

        Receipt receipt = argsParser.parseArgs(argGlob);

        ReceiptTextTranslator textTranslator = new ReceiptTextTranslator(
                new DateHandler(locale),
                new ReceiptCalculator());
        String receiptString = textTranslator.convertToText(receipt);

        try {
            Printer printer = PrinterFactory.getReceiptPrinter(PrinterType.TXT);
            printer.printReceipt(receiptString);
            System.out.println("Чек распечатан");
        } catch (IOException e) {
            System.out.println("Не удалось создать файл с чеком: " + e.getMessage());
        }
    }
}