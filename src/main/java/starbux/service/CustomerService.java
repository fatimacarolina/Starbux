package starbux.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import starbux.api.dto.Cart;
import starbux.repository.*;
import starbux.repository.entity.*;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomerService {
    private static final Logger LOG = LoggerFactory.getLogger(CustomerService.class);

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DrinkRepository drinkRepository;
    @Autowired
    ToppingRepository toppingRepository;
    @Autowired
    SoldDrinkRepository soldDrinkRepository;
    @Autowired
    SoldToppingRepository soldToppingRepository;

    public Cart confirmCart(Cart cart){
        LOG.info("Confirming cart {}", cart);
        applyDiscount(cart);
        Order order =new Order(null, cart.getOriginalAmount(), cart.getCusomterId().orElse(null));
        Set<SoldDrink>  soldDrinks = cart.getDrinkToToppings().entrySet().stream().map(e -> addToppingsToDrinks(e.getKey(), e.getValue(), order, cart.getCusomterId().orElse(null))).collect(Collectors.toSet());
        order.setDrinks(soldDrinks);
        orderRepository.save(order);
        return cart;
    }

    public Long getAmountOfOrdersPerCustomer(Long customerId){
        return orderRepository.countByCustomer(customerId);
    }

    public List<Drink> getAllDrinks(){
        return drinkRepository.findAll();
    }

    public List<Topping> getAllToppings(){
        return toppingRepository.findAll();
    }


    private SoldDrink addToppingsToDrinks(Drink drink, List<Topping> toppings, Order order, Long customerId){
        SoldDrink soldDrink = new SoldDrink(order, drink, customerId);
        Set<SoldTopping> soldToppings = toppings.stream().map(t -> new SoldTopping(soldDrink, t)).collect(Collectors.toSet());
        soldDrink.setToppings(soldToppings);
        return soldDrink;
    }

    private void applyDiscount(Cart cart){
        LOG.info("Applying discount to cart for customer: {}", cart.getCusomterId());
        double discount1 = getDiscountForCartTotalAmount(cart);
        double discount2 = getDiscountForCartTotalArticles(cart);
        if(discount1  ==0 && discount2 != 0){
            cart.setDiscountedAmount(discount2);
        }else{
            cart.setDiscountedAmount(discount1);
        }
        LOG.info("Discounted amount is {} for cart for customer: {}", cart.getDiscountedAmount(),cart.getCusomterId());
    }

    private double getDiscountForCartTotalAmount(Cart cart){
        if(cart.getOriginalAmount()>12.0){
            return 0.75* cart.getOriginalAmount();
        }
        return 0.0;
    }

    private Double getDiscountForCartTotalArticles(Cart cart){
        if(cart.getDrinkToToppings().entrySet().size()>=3){
            Map.Entry<Drink, List<Topping>> drinkWithLeastAmount = cart.getDrinkToToppings().entrySet().stream().min(Comparator.comparing(drinkListEntry -> drinkListEntry.getKey().getPrice())).get();
            return cart.getOriginalAmount() - drinkWithLeastAmount.getKey().getPrice() - drinkWithLeastAmount.getValue().stream().map(Product::getPrice).reduce(0.0, Double::sum);
        }
        return 0.0;
    }

}
