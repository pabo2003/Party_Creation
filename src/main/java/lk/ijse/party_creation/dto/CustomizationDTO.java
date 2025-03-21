package lk.ijse.party_creation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomizationDTO {
    private int id;
    private String email;
    private String name;
    private String address;
    private String phone;
    private String NICNumber;
    private UserDTO userDTO;
    private Date date;
}
