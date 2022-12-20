package by.bsuir.entity;

import java.util.Objects;

//Builder via inner class implementation
public class Product extends BaseEntity {
    private String description;
    private Double price;
    private Discount discount;

    private Product() {}

    public String getDescription() {
        return description;
    }

    public Double getPrice() {
        return price;
    }

    public Discount getDiscount() {
        return discount;
    }

    public static Builder builder() {
        return new Product().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(Long id) {
            Product.super.setId(id);
            return this;
        }

        public Builder setDescription(String title) {
            Product.this.description = title;
            return this;
        }

        public Builder setPrice(Double price) {
            Product.this.price = price;
            return this;
        }

        public Builder discount(Discount discount) {
            Product.this.discount = discount;
            return this;
        }

        public Builder defaultDiscount() {
            Product.this.discount = Discount.createDefault();
            return this;
        }

        public Product build() {
            return Product.this;
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        Product product = (Product) o;
        return Objects.equals(description, product.description) && Objects.equals(price, product.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), description, price);
    }

    @Override
    public String toString() {
        return "Product{" +
                "description='" + description + '\'' +
                ", price=" + price +
                '}';
    }
}