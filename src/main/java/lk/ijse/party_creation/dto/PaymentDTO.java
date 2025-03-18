package lk.ijse.party_creation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private int paymentID;
    private int orderID;
    private int userID;
    private String paymentMethod;
    private Double amount;
}
