package starbux.repository.entity;

import javax.persistence.*;

@Entity(name="sold_topping")
@Table(name = "sold_topping")
public class SoldTopping {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "sold_drink_id")
    private SoldDrink drink;
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name = "topping_id")
    private Topping topping;

    public SoldTopping() {
    }

    public SoldTopping(Long id, SoldDrink drink, Topping topping) {
        this.id = id;
        this.drink = drink;
        this.topping = topping;
    }

    public SoldTopping(SoldDrink drink, Topping topping) {
        this.drink = drink;
        this.topping = topping;
    }
}
