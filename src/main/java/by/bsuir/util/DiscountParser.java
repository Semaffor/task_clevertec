package by.bsuir.util;

import by.bsuir.entity.DiscountCard;
import by.bsuir.reader.Readable;

import java.util.ArrayList;
import java.util.List;

public class DiscountParser {

    public static final String DELIMITER_BETWEEN_ARGS = ";";
    public static final String ARGS_DELIMITER = "-";

    private final Readable reader;

    public DiscountParser(Readable reader) {
        this.reader = reader;
    }

    public List<DiscountCard> parseFromFile() {
        return parse(reader.readFile().split(DELIMITER_BETWEEN_ARGS));
    }

    public List<DiscountCard> parse(String[] args) {
        List<DiscountCard> cards = new ArrayList<>();
        for (String arg : args) {
            String[] splited = arg.split(ARGS_DELIMITER);
            cards.add(new DiscountCard(splited[0], Double.parseDouble(splited[1])));
        }
        return cards;
    }
}
