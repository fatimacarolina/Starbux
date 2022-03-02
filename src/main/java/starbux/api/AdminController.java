package starbux.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import starbux.repository.entity.Drink;
import starbux.repository.entity.Topping;
import starbux.service.AdminService;
import starbux.service.CustomerService;

@RestController
@RequestMapping("/admin")
@PreAuthorize("isAuthenticated()")
public class AdminController {

    @Autowired
    AdminService adminService;
    @Autowired
    CustomerService customerService;

    @PostMapping("/drink")
    public Drink createDrink(@RequestBody Drink drink){
        return adminService.createDrink(drink);
    }

    @PutMapping("/drink/{id}")
    public Drink updateDrink(@RequestBody Drink drink, @PathVariable Long id){
        return adminService.updateDrink(id, drink);
    }

    @DeleteMapping("/drink/{id}")
    public void deleteDrink(@PathVariable Long id){
        adminService.deleteDrink(id);
    }

    @PostMapping("/topping")
    public Topping createTopping(@RequestBody Topping topping){
        return adminService.createTopping(topping);
    }

    @PutMapping("/topping/{id}")
    public Topping updateTopping(@RequestBody Topping topping, @PathVariable Long id){
        return adminService.updateTopping(id, topping);
    }

    @DeleteMapping("/topping/{id}")
    public void deleteTopping(@PathVariable Long id){
        adminService.deleteTopping(id);
    }

    @GetMapping("/order/customer/{id}")
    public Long getAmountOfOrdersPerCustomer(@PathVariable Long id){
        return customerService.getAmountOfOrdersPerCustomer(id);
    }

    @GetMapping("/topping/most-used")
    public Topping getMostUsedTopping(){
        return adminService.getMostUsedTopping();
    }
}
