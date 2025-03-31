package lk.ijse.party_creation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ContactDTO {
    private String name;
    private String email;
    private String address;
    private String phone;
    private String nicNumber;
    private String message;
}
