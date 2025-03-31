package lk.ijse.party_creation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "contact")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class Contact {
    @Column(nullable = false)
    private String name;

    @Id
    @Column(nullable = false)
    private String email;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String phone;

    @Column(nullable = false)
    private String message;

}
