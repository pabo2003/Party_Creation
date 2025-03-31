package lk.ijse.party_creation.service.Impl;

import lk.ijse.party_creation.dto.ContactDTO;
import lk.ijse.party_creation.entity.Contact;
import lk.ijse.party_creation.repo.ContactRepo;
import lk.ijse.party_creation.service.ContactService;
import lk.ijse.party_creation.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ContactServiceImpl implements ContactService {
    @Autowired
    private ContactRepo contactRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveContact(ContactDTO contactDTO) {
        contactRepo.save(modelMapper.map(contactDTO, Contact.class));
        return VarList.Created;
    }
}
