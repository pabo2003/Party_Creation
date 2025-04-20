package lk.ijse.party_creation.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class CartDTO {
    private int id;
    private int productId;
    private String email;
    private int quantity;


}
