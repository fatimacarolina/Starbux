package starbux.repository.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.io.Serializable;

@Entity(name="drink")
@Table(name = "drink")
public class Drink extends Product implements Serializable {

    public Drink() {
    }

    public Drink(String name, Double price) {
        super(null, name, price);
    }


}