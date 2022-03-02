package starbux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import starbux.repository.entity.SoldTopping;
import starbux.repository.entity.Topping;

public interface SoldToppingRepository extends JpaRepository<SoldTopping, Long> {
    long countByTopping(Topping topping);
}