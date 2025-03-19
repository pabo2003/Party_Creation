package lk.ijse.party_creation.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "orderDetails")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class OrderDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int orderDetailID;

    @ManyToOne
    @JoinColumn(name = "orderID", nullable = false, referencedColumnName = "orderID")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "productID", nullable = false, referencedColumnName = "productID")
    private Product product;
}