package lk.ijse.party_creation.dto;

import lk.ijse.party_creation.entity.OrderDetail;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class PaymentDTO {
    private int paymentID;
    private OrderDetail orderDetail;
    private String paymentMethod;
    private Double amount;
    private String userEmail;
}
