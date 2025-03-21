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
    private int productID;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private double price;

    @Column(nullable = false)
    private int stock;

    @Column(nullable = false)
    private String description;

    private String fileName;
    private String filetype;
    @Lob
    private byte[] data;

    @OneToMany(mappedBy = "product")
    private List<OrderDetail> orderDetails;
}