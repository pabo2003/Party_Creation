package lk.ijse.party_creation.repo;

import lk.ijse.party_creation.dto.ProductDTO;
import lk.ijse.party_creation.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ProductRepo extends JpaRepository<Product,Integer>{
    @Query("SELECT i.productID FROM Product i") // Corrected JPQL query
    List<String> getProductIds();


    @Query("SELECT new lk.ijse.party_creation.dto.ProductDTO(i.productID, i.name, i.category, i.price, i.stock, i.description, i.image) FROM Product i WHERE i.productID = :code")
    List<ProductDTO> findProductById(@Param("code") int productId);

    @Modifying
    @Query("UPDATE Product SET stock = stock - :quantity where productID = :code")
    void updateQty(String code, int quantity);


}
