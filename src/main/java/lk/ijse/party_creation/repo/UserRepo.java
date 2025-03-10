package lk.ijse.party_creation.repo;

import lk.ijse.party_creation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<User,String> {

    User findByEmail(String email);
    boolean existsByEmail(String email);
    int deleteByEmail(String email);
}
