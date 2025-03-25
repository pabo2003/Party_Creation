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
    private int customId;
    private Date orderDate;
    private double totalAmount;
    private String status;

    @Override
    public String toString() {
        return "OrderDTO{" +
                "orderID=" + orderID +
                ", customId=" + customId +
                ", orderDate=" + orderDate +
                ", totalAmount=" + totalAmount +
                ", status='" + status + '\'' +
                '}';
    }
}
