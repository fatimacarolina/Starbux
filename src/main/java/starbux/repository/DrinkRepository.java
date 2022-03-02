package starbux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import starbux.repository.entity.Drink;

public interface DrinkRepository extends JpaRepository<Drink, Long> {

}
