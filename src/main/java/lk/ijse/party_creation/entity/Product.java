package lk.ijse.party_creation.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Table(name = "product")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productID;

    private String name;
    private String category;
    private double price;
    private int stock;
    private String description;
    private String image;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;
}
