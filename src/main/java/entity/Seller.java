package entity;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@NamedQuery(name = "invoices", query = "select i from Invoice i where i.seller.id = :seller_id order by i.date")
@Table(name = "sellers")
public class Seller {
    @Id
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "salary")
    private int salary;

    //drugi kierunek związku jeden-do-wielu
    //fetch decydyje o pobieraniu faktur
    //EAGER - faktury są natychmiast pobierane razem z encją
    //LAZY - faktury są pobierane dopiero gdy wywołamy getInvoices() na encji
    @OneToMany(mappedBy = "seller", fetch = FetchType.EAGER)
    private Set<Invoice> invoices = new HashSet<>();

    public Set<Invoice> getInvoices() {
        return invoices;
    }

    public Seller(String firstName, String lastName, LocalDate birthDate, int salary){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.salary = salary;
    }

    public Seller() {
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public int getSalary() {
        return salary;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    @Override
    public String toString() {
        return "Seller{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", birthDate=" + birthDate +
                ", salary=" + salary +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Seller seller = (Seller) o;
        return getId() == seller.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId());
    }
}
