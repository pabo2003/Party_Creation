package lk.ijse.party_creation.repo;

import lk.ijse.party_creation.entity.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrderDetailRepo extends JpaRepository<OrderDetail,Integer> {
}
