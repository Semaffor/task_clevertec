package by.bsuir.service;

import by.bsuir.entity.DiscountCard;

public interface CardService {

    DiscountCard findByCardName(String name);
}
