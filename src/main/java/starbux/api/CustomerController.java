package starbux.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import starbux.api.dto.Cart;
import starbux.repository.entity.Drink;
import starbux.repository.entity.Topping;
import starbux.service.CustomerService;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @PostMapping("/cart/confirm")
    public Cart confirmOrder(@RequestBody Cart cart){
        return customerService.confirmCart(cart);
    }

    @GetMapping("/product/drink")
    public List<Drink> getAllDrinks(){
        return customerService.getAllDrinks();
    }

    @GetMapping("/product/topping")
    public List<Topping> getAllToppings(){
        return customerService.getAllToppings();
    }

}
