package by.bsuir.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Receipt extends BaseEntity {
    private String cashierName;
    private Date date;
    private List<Order> orders;
    private DiscountCard card;

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public List<Order> getOrders() {
        return orders;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public DiscountCard getCard() {
        return card;
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
        Receipt receipt = (Receipt) o;
        return Objects.equals(cashierName, receipt.cashierName) && Objects.equals(date,
                receipt.date) && Objects.equals(orders, receipt.orders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cashierName, date, orders);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "cashierName='" + cashierName + '\'' +
                ", date=" + date +
                ", orders=" + orders +
                ", card=" + card +
                '}';
    }

    public static Receipt.Builder builder() {
        return new Receipt().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Builder id(Long id) {
            Receipt.super.setId(id);
            return this;
        }

        public Builder orders(List<Order> orders) {
            Receipt.this.orders = orders;
            return this;
        }

        public Builder discountCard(DiscountCard discountCard) {
            Receipt.this.card = discountCard;
            return this;
        }

        public Builder cashierName(String cashierName) {
            Receipt.this.cashierName = cashierName;
            return this;
        }

        public Builder date(Date date) {
            Receipt.this.date = date;
            return this;
        }

        public Receipt build() {
            return Receipt.this;
        }
    }
}
