package lk.ijse.party_creation.repo;

import lk.ijse.party_creation.entity.Customization;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomizationRepo extends JpaRepository<Customization,Integer> {
    boolean existsByEmail(String email);
    Customization findByEmail(String email);
    void deleteByEmail(String email);
}
