package lk.ijse.party_creation.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class Transaction2DTO {
    private double totalAmount;
    private String fullName;
    private String email;
    private String userEmail;
    private String address;
    private String city;
    private String zipCode;

}
