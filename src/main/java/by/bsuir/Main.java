package by.bsuir;

import by.bsuir.entity.Receipt;
import by.bsuir.generator.DiscountGenerator;
import by.bsuir.generator.ProductGenerator;
import by.bsuir.service.*;
import by.bsuir.service.impl.CardServiceImpl;
import by.bsuir.service.impl.MenuServiceImpl;
import by.bsuir.service.impl.OrderServiceImpl;
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

//    static String[] argGlob = {"1-2", "1-4", "card-1234"};
    static String[] argGlob = {"1-2", "3-4", "5-6", "3-1", "2-1", "card-1234"};

    public static void main(String[] args) {

        Random random = new Random();
        MenuService menuService = new MenuServiceImpl(new ProductGenerator(random, new DiscountGenerator(random)));
        OrderService orderService = new OrderServiceImpl();
        CardService cardService = new CardServiceImpl();

        ArgsParser argsParser = new ArgsParser(menuService, cardService, orderService);

        Receipt receipt = argsParser.parseReceipt(argGlob);

        ReceiptTextTranslator textTranslator = new ReceiptTextTranslator(
                new DateHandler(locale),
                new ReceiptCalculator());
        String receiptString = textTranslator.convertInText(receipt);

        try {
            Printer printer = PrinterFactory.getReceiptPrinter(PrinterType.TXT);
            printer.printReceipt(receiptString);
        } catch (IOException e) {
            System.out.println("Не удалось создать файл с чеком: " + e.getMessage());
        }
    }
}