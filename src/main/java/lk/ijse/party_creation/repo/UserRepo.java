package lk.ijse.party_creation.repo;

import lk.ijse.party_creation.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, String> {

    User findByEmail(String email);  // Find user by email
    boolean existsByEmail(String email);  // Check if email exists
    int deleteByEmail(String email);  // Delete user by email (optional)
}
