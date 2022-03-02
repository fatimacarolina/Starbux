package starbux.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name="topping")
@Table(name = "topping")
public class Topping extends Product implements Serializable {
    public Topping() {
    }

    public Topping(String name, Double price) {
        super(null, name, price);
    }
}