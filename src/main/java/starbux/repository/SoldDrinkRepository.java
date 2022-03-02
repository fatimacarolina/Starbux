package starbux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import starbux.repository.entity.SoldDrink;

public interface SoldDrinkRepository extends JpaRepository<SoldDrink, Long> {

}
