package lk.ijse.party_creation.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CustomizationDTO {
    private int customID;
    private int orderID;
    private int userID;
    private String text;
    private String image;
    private String fontStyle;

}
