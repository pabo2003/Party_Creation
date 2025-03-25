package lk.ijse.party_creation.repo;

import lk.ijse.party_creation.entity.Order;
import lk.ijse.party_creation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Integer> {
    Order findByOrderID(int orderID);
}
