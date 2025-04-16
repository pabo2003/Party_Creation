package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.ContactDTO;
import lk.ijse.party_creation.dto.ResponseDTO;
import lk.ijse.party_creation.service.ContactService;
import lk.ijse.party_creation.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/contact")
public class ContactController {
    @Autowired
    private final ContactService contactService;

    public ContactController(ContactService contactService) {
        this.contactService = contactService;
    }

    @PostMapping("/save")
    public ResponseEntity<ResponseDTO> saveContact(@RequestBody ContactDTO contactDTO){
        try{
            int res = contactService.saveContact(contactDTO);
            if(res == VarList.Created){
                return ResponseEntity.ok(new ResponseDTO(VarList.Created, "Contact saved successfully", contactDTO));
            } else {
                return ResponseEntity.status(500).body(new ResponseDTO(VarList.Internal_Server_Error, "Failed to save contact", null));
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
