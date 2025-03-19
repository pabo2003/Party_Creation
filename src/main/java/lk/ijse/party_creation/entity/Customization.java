package lk.ijse.party_creation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Table(name = "customization")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
public class Customization implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int customID;

    @ManyToOne
    @JoinColumn(name = "orderID", nullable = false, referencedColumnName = "orderID")
    private Order order; // Many customizations can belong to one order

    @ManyToOne
    @JoinColumn(name = "userID", nullable = false, referencedColumnName = "userID")
    private User user; // Many customizations can belong to one user

    private String text; // Custom text for the order
    private String image; // Custom image for the order
    private String fontStyle; // Font style used for custom text
}