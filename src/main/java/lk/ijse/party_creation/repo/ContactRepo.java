package lk.ijse.party_creation.repo;

import lk.ijse.party_creation.entity.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Integer> {
}
