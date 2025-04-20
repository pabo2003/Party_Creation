package lk.ijse.party_creation.repo;

import lk.ijse.party_creation.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CartRepo extends JpaRepository<Cart,Integer> {
    List<Cart> findByEmail(String email);

    void deleteByEmail(String email);
}
