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
    @JoinColumn(name = "orderDetailID", nullable = false, referencedColumnName = "orderDetailID")
    private OrderDetail orderDetail;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private Double amount;
    private String userEmail;
}
