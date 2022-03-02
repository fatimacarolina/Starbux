package starbux.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import starbux.api.dto.Cart;
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
class AdminServiceTest {
    @Autowired
    AdminService adminService;
    @Autowired
    CustomerService customerService;


    @Test
    void testCreateDrink(){
        Drink expectedDrink = new Drink("Mocha", 4.0);
        Drink actualDrink = adminService.createDrink(expectedDrink);
        assertThat(actualDrink, equalTo(expectedDrink));
    }

    @Test
    void testUpdateDrink(){
        Drink drinkToUpdate = adminService.createDrink(new Drink("Mocha", 4.0));
        drinkToUpdate.setName("Not a mocha");
        Drink actualDrink = adminService.updateDrink(drinkToUpdate.getId(), drinkToUpdate);
        assertThat(actualDrink, equalTo(drinkToUpdate));
    }

    @Test
    void testCreateTopping(){
        Topping expectedTopping = new Topping("Sprinkles", 4.0);
        Topping actualTopping = adminService.createTopping(expectedTopping);
        assertThat(actualTopping, equalTo(expectedTopping));
    }

    @Test
    void testUpdateTopping(){
        Topping toppingToUpdate = adminService.createTopping(new Topping("Sprinkles", 4.0));
        toppingToUpdate.setName("Jam");
        Topping actualTopping = adminService.updateTopping(toppingToUpdate.getId(), toppingToUpdate);
        assertThat(actualTopping, equalTo(toppingToUpdate));
    }

    @Test
    void testGetMostUsedTopping(){
        List<Topping> toppings1 = new ArrayList<>();
        Drink drink1 = new Drink("Black Coffee", 4.0);
        Drink drink2 = new Drink("Latte", 5.0);
        toppings1.add(new Topping("Milk", 2.0));
        toppings1.add(new Topping("Milk", 2.0));
        toppings1.add(new Topping("Lemon", 2.0));
        HashMap<Drink, List<Topping>> drinkToToppingMap = new HashMap<>();
        drinkToToppingMap.put(drink1, toppings1);
        drinkToToppingMap.put(drink2, toppings1);
        customerService.confirmCart(new Cart(drinkToToppingMap,17.0, Optional.of(1L)));
        assertThat(adminService.getMostUsedTopping(), equalTo(new Topping("Milk", 2.0)));
    }
}