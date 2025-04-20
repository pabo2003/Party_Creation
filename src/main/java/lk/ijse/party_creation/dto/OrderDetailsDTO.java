package lk.ijse.party_creation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class OrderDetailsDTO {
    private int orderDetailID;
    private OrderDTO order;
    private ProductDTO product;
    private int quantity;
    private double price;
}
