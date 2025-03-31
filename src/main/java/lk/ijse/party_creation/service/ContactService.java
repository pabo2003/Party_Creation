package lk.ijse.party_creation.service;

import lk.ijse.party_creation.dto.ContactDTO;
import org.springframework.stereotype.Service;

@Service
public interface ContactService {
    int saveContact(ContactDTO contactDTO);

}
