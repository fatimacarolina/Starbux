package starbux.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import starbux.repository.DrinkRepository;
import starbux.repository.SoldToppingRepository;
import starbux.repository.ToppingRepository;
import starbux.repository.entity.Drink;
import starbux.repository.entity.Topping;

import java.util.List;
import java.util.Objects;

@Service
public class AdminService {
    private static final Logger LOG = LoggerFactory.getLogger(AdminService.class);

    @Autowired
    DrinkRepository drinkRepository;
    @Autowired
    ToppingRepository toppingRepository;
    @Autowired
    SoldToppingRepository soldToppingRepository;

    public Drink createDrink(Drink drink){
        LOG.info("Creating new drink: {}", drink);
        return drinkRepository.save(drink);
    }

    public Drink updateDrink(Long drinkId, Drink drink){
        LOG.info("Updating drink with id={} to: {}", drinkId, drink);
        if(!Objects.equals(drinkId, drink.getId())){
            throw new IllegalStateException("Drink ID given doesn't match that of the object to update");
        }
        return drinkRepository.save(drink);
    }

    public void deleteDrink(Long drinkId){
        LOG.info("Deleting drink with id={}", drinkId);
        drinkRepository.deleteById(drinkId);
    }

    public Topping createTopping(Topping topping){
        LOG.info("Creating new topping: {}", topping);
        return toppingRepository.save(topping);
    }

    public Topping updateTopping(Long toppingId, Topping topping){
        LOG.info("Updating topping with id={} to: {}", toppingId, topping);
        if(topping.getId()!=null && !Objects.equals(toppingId, topping.getId())){
            throw new IllegalStateException("Drink ID given doesn't match that of the object to update");
        }
        return toppingRepository.save(topping);
    }

    public void deleteTopping(Long toppingId){
        LOG.info("Deleting topping with id={}", toppingId);
        toppingRepository.deleteById(toppingId);
    }

    public Topping getMostUsedTopping(){
        List<Topping> allToppings = toppingRepository.findAll();
        Long max = 0L;
        Topping maxTopping = new Topping();
        for(Topping topping: allToppings){
            Long count = soldToppingRepository.countByTopping(topping);
            if(count>max){
                max = count;
                maxTopping = topping;
            }
        }
        return maxTopping;
    }





}
