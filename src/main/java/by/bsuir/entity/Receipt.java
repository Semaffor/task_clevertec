package by.bsuir.entity;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Receipt extends BaseEntity {
    private String cashierName;
    private Date date;
    private List<Order> orders;
    private String cardName;

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

    public String getCardName() {
        return cardName;
    }

    public void setCardName(String cardName) {
        this.cardName = cardName;
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
        return Objects.hash(super.hashCode(), cashierName, date, orders, cardName);
    }

    @Override
    public String toString() {
        return "Receipt{" +
                "cashierName='" + cashierName + '\'' +
                ", date=" + date +
                ", orders=" + orders +
                ", cardName='" + cardName + '\'' +
                '}';
    }

    public static Receipt.Builder builder() {
        return new Receipt().new Builder();
    }

    public class Builder {

        private Builder() {
        }

        public Receipt.Builder id(Long id) {
            Receipt.super.setId(id);
            return this;
        }

        public Receipt.Builder orders(List<Order> orders) {
            Receipt.this.orders = orders;
            return this;
        }

        public Receipt.Builder cardName(String cardName) {
            Receipt.this.cardName = cardName;
            return this;
        }

        public Receipt.Builder cashierName(String cashierName) {
            Receipt.this.cashierName = cashierName;
            return this;
        }

        public Receipt.Builder date(Date date) {
            Receipt.this.date = date;
            return this;
        }

        public Receipt build() {
            return Receipt.this;
        }
    }
}
