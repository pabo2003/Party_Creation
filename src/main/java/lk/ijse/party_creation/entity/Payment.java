package lk.ijse.party_creation.entity;

import jakarta.persistence.*;
import lombok.*;

@Table(name = "payment")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int paymentID;

    @OneToOne
    @JoinColumn(name = "orderID", nullable = false)
    private Order order;

    private int userID;
    private String paymentMethod;
    private Double amount;

}
