package starbux.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import starbux.repository.entity.Topping;

public interface ToppingRepository extends JpaRepository<Topping, Long> {

}