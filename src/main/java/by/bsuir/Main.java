package by.bsuir;

import by.bsuir.entity.Receipt;
import by.bsuir.generator.DiscountGenerator;
import by.bsuir.generator.ProductGenerator;
import by.bsuir.printer.Printer;
import by.bsuir.printer.PrinterFactory;
import by.bsuir.printer.PrinterType;
import by.bsuir.reader.BasicReader;
import by.bsuir.reader.TextReader;
import by.bsuir.service.CardService;
import by.bsuir.service.MenuService;
import by.bsuir.service.OrderService;
import by.bsuir.service.impl.CardServiceImpl;
import by.bsuir.service.impl.MenuServiceImpl;
import by.bsuir.service.impl.OrderServiceImpl;
import by.bsuir.util.*;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class Main {

    //better to set into properties
    public static final Locale locale= new Locale("ru", "RU");

    static String[] argGlob = {"1-2", "3-4", "5-6", "3-1", "2-1", "card-1234"};
    public static final String FILE_WITH_CARDS_NAME = "discountCard.txt";

    public static void main(String[] args) {
        List<String> argsList = Arrays.asList(args);

        Random random = new Random();
        MenuService menuService = new MenuServiceImpl(new ProductGenerator(random, new DiscountGenerator(random)));
        OrderService orderService = new OrderServiceImpl();

        String fileWithDiscountCards = argsList.get(0).split("=")[1];
        //Decorator
        DiscountParser discountParser = new DiscountParser(new TextReader(new BasicReader(fileWithDiscountCards)));
        CardService cardService = new CardServiceImpl(discountParser.parseFromFile());

        ArgsParser argsParser = new ArgsParser(menuService, cardService, orderService);
        Receipt receipt = argsParser.parseReceipt(argsList.subList(1, argsList.size()));
//        Receipt receipt = argsParser.parseReceipt(Arrays.asList(argGlob));

        ReceiptTextTranslator textTranslator = new ReceiptTextTranslator(
                new DateHandler(locale),
                new ReceiptCalculator());
        String receiptString = textTranslator.convertInText(receipt);

        try {
            Printer printer = PrinterFactory.getReceiptPrinter(PrinterType.TXT);
            printer.printReceipt(receiptString);
        } catch (IOException e) {
            System.out.println("Failed to create a receipt file: " + e.getMessage());
        }
    }
}