package lk.ijse.party_creation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "`orders`")
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

    @ManyToOne
    @JoinColumn(name = "customID", nullable = false)
    private Customization customization;

    @Column(nullable = false)
    private String orderDate;

    @Column(nullable = false)
    private double totalAmount;

    @Column(nullable = false)
    private String status;

    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;

  /*  @OneToOne(mappedBy = "order")
    private Payment payment;*/
}