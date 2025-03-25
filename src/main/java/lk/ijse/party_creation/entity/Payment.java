/*
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

    @Column(nullable = false)
    private int orderID;

    @Column(nullable = false)
    private int userID;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private Double amount;
}*/
