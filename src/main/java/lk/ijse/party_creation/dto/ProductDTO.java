package lk.ijse.party_creation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDTO {
    private int productID;
    private String name;
    private String category;
    private double price;
    private int stock;
    private String description;
    private String image;
}
