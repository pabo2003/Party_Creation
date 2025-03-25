package lk.ijse.party_creation.repo;

import lk.ijse.party_creation.dto.ProductDTO;
import lk.ijse.party_creation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer>{
  /*  @Query("SELECT i.productID FROM Product i") // Corrected JPQL query
    List<String> getProductIds();

    @Modifying
    @Query("UPDATE Product SET stock = stock - :quantity where productID = :code")
    void updateQty(String code, int quantity);

*/
}