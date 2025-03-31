package lk.ijse.party_creation.repo;

import lk.ijse.party_creation.entity.PartyCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Locale;

@Repository
public interface CategoryRepo extends JpaRepository<PartyCategory,Integer> {
}
