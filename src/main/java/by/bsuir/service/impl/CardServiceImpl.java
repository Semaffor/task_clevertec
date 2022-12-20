package by.bsuir.service.impl;

import by.bsuir.entity.DiscountCard;
import by.bsuir.exception.NotFoundException;
import by.bsuir.service.CardService;
import by.bsuir.service.CrudOperations;

import java.util.List;
import java.util.Objects;

public class CardServiceImpl implements CardService {

    //probably it should be in repository layer, but for brevity I used here
    private List<DiscountCard> cards = List.of(
            new DiscountCard("1234", 5),
            new DiscountCard("4321", 1)
        );

    public CardServiceImpl(List<DiscountCard> cards) {
        this.cards = cards;
    }

    public DiscountCard findByCardName(String name) {
        return cards.stream()
                .filter(card -> Objects.equals(card.cardName(), name))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Card with name=%s hasn't found", name)));
    }

    public void setCards(List<DiscountCard> cards) {
        this.cards = cards;
    }
}
