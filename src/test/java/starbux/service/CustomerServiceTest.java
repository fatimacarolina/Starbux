package starbux.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import starbux.api.dto.Cart;
import starbux.repository.*;
import starbux.repository.entity.Drink;
import starbux.repository.entity.Topping;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@AutoConfigureMockMvc
@Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:data.sql")
class CustomerServiceTest {

    @Autowired
    OrderRepository orderRepository;
    @Autowired
    DrinkRepository drinkRepository;
    @Autowired
    ToppingRepository toppingRepository;
    @Autowired
    CustomerService customerService;
    @Autowired
    SoldDrinkRepository soldDrinkRepository;
    @Autowired
    SoldToppingRepository soldToppingRepository;


    @Test
    void confirmOrder(){
        List<Topping> toppings1 = new ArrayList<>();
        Drink drink1 = new Drink("Black Coffee", 4.0);
        Drink drink2 = new Drink("Latte", 5.0);
        toppings1.add(new Topping("Milk", 2.0));
        toppings1.add(new Topping("Lemon", 2.0));
        HashMap<Drink, List<Topping>> drinkToToppingMap = new HashMap<>();
        drinkToToppingMap.put(drink1, toppings1);
        drinkToToppingMap.put(drink2, toppings1);
        Cart expectedCart = new Cart(drinkToToppingMap, 17.0, 12.75, Optional.empty());
        Cart actualCart = customerService.confirmCart(new Cart(drinkToToppingMap,17.0, Optional.empty()));
        assertThat(actualCart, equalTo(expectedCart));
    }

    @Test
    void getAmountOfOrdersPerCustomer(){
        List<Topping> toppings1 = new ArrayList<>();
        Drink drink1 = new Drink("Black Coffee", 4.0);
        Drink drink2 = new Drink("Latte", 5.0);
        toppings1.add(new Topping("Milk", 2.0));
        toppings1.add(new Topping("Lemon", 2.0));
        HashMap<Drink, List<Topping>> drinkToToppingMap = new HashMap<>();
        drinkToToppingMap.put(drink1, toppings1);
        drinkToToppingMap.put(drink2, toppings1);
        customerService.confirmCart(new Cart(drinkToToppingMap,17.0, Optional.of(1L)));
        assertThat(customerService.getAmountOfOrdersPerCustomer(1L), equalTo(1L));
    }
}