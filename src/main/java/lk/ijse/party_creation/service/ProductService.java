package lk.ijse.party_creation.service;

import lk.ijse.party_creation.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    int saveProduct(ProductDTO product, MultipartFile file);
    int deleteProduct(int productID);
    int updateProduct(ProductDTO productDTO);
    ProductDTO getProductById(int productID);
    List<ProductDTO> getAllProducts();
}