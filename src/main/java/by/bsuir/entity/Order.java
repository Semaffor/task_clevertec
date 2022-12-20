package by.bsuir.entity;

import java.util.Objects;

public class Order extends BaseEntity {
    private Product product;
    private int quantity;

    private Order() {
    }

    public double getTotalPrice() {
        return product.getPrice() * quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Order order)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        return quantity == order.quantity && Objects.equals(product, order.product);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), product, quantity);
    }

    @Override
    public String toString() {
        return "Order{" +
                "product=" + product +
                ", quantity=" + quantity +
                '}';
    }

    public static Order.Builder builder() {
        return new Order().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(Long id) {
            Order.super.setId(id);
            return this;
        }

        public Builder product(Product product) {
            Order.this.product = product;
            return this;
        }

        public Builder quantity(int quantity) {
            Order.this.quantity = quantity;
            return this;
        }

        public Order build() {
            return Order.this;
        }
    }
}
