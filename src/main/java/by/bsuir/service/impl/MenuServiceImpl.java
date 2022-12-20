package by.bsuir.service.impl;

import by.bsuir.entity.Product;
import by.bsuir.exception.NotFoundException;
import by.bsuir.service.MenuService;
import by.bsuir.generator.ProductGenerator;

import java.util.List;
import java.util.Objects;

public class MenuServiceImpl implements MenuService {

    //probably it should be in repository layer, but for brevity I used here
    private final List<Product> menu;

    public MenuServiceImpl(ProductGenerator productGenerator) {
        menu = productGenerator.nextProductList();
    }

    public Product findById(Long id) {
       return menu.stream()
                .filter(product -> Objects.equals(product.getId(), id))
                .findFirst()
                .orElseThrow(() -> new NotFoundException(String.format("Товар с id=%s не найден", id)));
    }
}
