package entity;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@NamedQuery(name = "monthSummary",
        query = "select sum(i.price) from Invoice i where i.seller.id = :seller_id " +
                "and Year(i.date) = :year " +
                "and Month(i.date) = :month")
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @CreationTimestamp
    private LocalDateTime date;

    @ManyToOne
    private Seller seller;

    private BigDecimal price;

    private String customer;

    public Invoice(Seller seller, BigDecimal price, String customer){
        this.customer = customer;
        this.seller = seller;
        this.price = price;
    }

    public Invoice() {
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public Seller getSeller() {
        return seller;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getCustomer() {
        return customer;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public void setSeller(Seller seller) {
        this.seller = seller;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setCustomer(String customer) {
        this.customer = customer;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "id=" + id +
                ", date=" + date +
                ", seller=" + seller.getId() +
                ", price=" + price +
                ", customer='" + customer + '\'' +
                '}';
    }
//    Metoda wywoływana uatomatycznie tuż przed itrwaleniem encji
//    @PrePersist
//    private void initDate(){
//        //Niemożliwe do testowania
//        this.date = LocalDateTime.now();
//    }


    @Override
    public boolean equals(Object o) {
        System.out.println("EQUALS");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return getId() == invoice.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
