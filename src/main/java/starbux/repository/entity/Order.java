package starbux.repository.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name="order_entity")
@Table(name = "order_entity")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(
            name = "total_amount"
    )
    private Double totalAmount;

    @OneToMany(mappedBy="order", cascade=CascadeType.ALL)
    private Set<SoldDrink> drinks;
    @Column(
            name = "customer_id"
    )
    private Long customer;

    public Order() {
    }

    public Order(Long id, Double totalAmount, Long customer) {
        this.id = id;
        this.totalAmount = totalAmount;
        this.customer = customer;
    }

    public Order(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public void setDrinks(Set<SoldDrink> drinks){
        this.drinks = drinks;
    }

    public Set<SoldDrink> getDrinks() {
        return drinks;
    }
}
