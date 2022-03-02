package starbux.api.dto;

import starbux.repository.entity.Drink;
import starbux.repository.entity.Topping;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

public class Cart {
    private Map<Drink, List<Topping>> drinkToToppings;
    private Double originalAmount;
    private Double discountedAmount;
    private Optional<Long> cusomterId;

    public Cart(Map<Drink, List<Topping>> drinkToToppings, Double originalAmount, Double discountedAmount, Optional<Long> cusomterId) {
        this.drinkToToppings = drinkToToppings;
        this.originalAmount = originalAmount;
        this.discountedAmount = discountedAmount;
        this.cusomterId = cusomterId;
    }

    public Cart(Map<Drink, List<Topping>> drinkToToppings, Double totalAmount, Optional<Long> cusomterId) {
        this.drinkToToppings = drinkToToppings;
        this.originalAmount = totalAmount;
        this.discountedAmount = totalAmount;
        this.cusomterId = cusomterId;
    }

    public Map<Drink, List<Topping>> getDrinkToToppings() {
        return drinkToToppings;
    }

    public void setDrinkToToppings(Map<Drink, List<Topping>> drinkToToppings) {
        this.drinkToToppings = drinkToToppings;
    }

    public Double getOriginalAmount() {
        return originalAmount;
    }

    public void setOriginalAmount(Double originalAmount) {
        this.originalAmount = originalAmount;
    }

    public Double getDiscountedAmount() {
        return discountedAmount;
    }

    public void setDiscountedAmount(Double discountedAmount) {
        this.discountedAmount = discountedAmount;
    }

    public Optional<Long> getCusomterId() {
        return cusomterId;
    }

    public void setCusomterId(Optional<Long> cusomterId) {
        this.cusomterId = cusomterId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return Objects.equals(drinkToToppings, cart.drinkToToppings) && Objects.equals(originalAmount, cart.originalAmount) && Objects.equals(discountedAmount, cart.discountedAmount) && Objects.equals(cusomterId, cart.cusomterId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(drinkToToppings, originalAmount, discountedAmount, cusomterId);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "drinkToToppings=" + drinkToToppings +
                ", originalAmount=" + originalAmount +
                ", discountedAmount=" + discountedAmount +
                ", cusomterId=" + cusomterId +
                '}';
    }
}
