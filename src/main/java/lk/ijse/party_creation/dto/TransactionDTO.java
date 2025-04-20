package lk.ijse.party_creation.dto;

import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class TransactionDTO {
    private OrderDTO orderDTO;
    private PaymentDTO paymentDTO;
    private List<OrderDetailsDTO> odList;
}
