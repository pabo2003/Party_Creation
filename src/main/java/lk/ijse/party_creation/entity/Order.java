package lk.ijse.party_creation.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "order")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderID;

    private int userID;
    private String orderDate;
    private double totalAmount;
    private String status;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

    @OneToOne(mappedBy = "order")
    private Payment payment;
}
