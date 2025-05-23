package lk.ijse.party_creation.dto;

import lombok.*;

import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class OrderDTO {
    private int orderID;
    private UserDTO user;
    private Date orderDate;
    private double totalAmount;
    private String status;
    private String fullName;
    private String email;
    private String address;
    private String city;
    private String zipCode;
}
