package starbux.repository.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name="sold_drink")
@Table(name = "sold_drink")
public class SoldDrink {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Order order;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "drink_id")
    private Drink drink;
    @Column(
            name = "customer_id"
    )
    private Long customerId;

    @OneToMany(mappedBy="drink", cascade=CascadeType.ALL)
    private Set<SoldTopping> toppings;

    public SoldDrink() {
    }

    public SoldDrink(Long id, Order order, Drink drink, Long customerId) {
        this.id = id;
        this.order = order;
        this.drink = drink;
        this.customerId = customerId;
    }

    public SoldDrink(Order order, Drink drink, Long customerId) {
        this.order = order;
        this.drink = drink;
        this.customerId = customerId;
    }

    public void setToppings(Set<SoldTopping> toppings){
        this.toppings = toppings;
    }

    public Set<SoldTopping> getToppings() {
        return toppings;
    }
}
