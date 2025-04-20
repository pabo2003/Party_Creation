package lk.ijse.party_creation.repo;

import lk.ijse.party_creation.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
    Order findByOrderID(int orderID);
}
