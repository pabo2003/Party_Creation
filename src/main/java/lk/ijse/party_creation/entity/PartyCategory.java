package lk.ijse.party_creation.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Table(name = "category")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
@Entity
public class PartyCategory {
    @Id
    private int categoryID;

    @Column(nullable = false)
    private String name;
}
