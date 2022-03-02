package starbux.repository;

import org.springframework.data.repository.CrudRepository;
import starbux.repository.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
    long countByCustomer(Long customerId);
}
