package lk.ijse.party_creation.service;

import lk.ijse.party_creation.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public int saveProduct(ProductDTO product, MultipartFile file);
    public int deleteProduct(int productID);
    public ProductDTO updateProduct(int productID, ProductDTO productDTO);
    public ProductDTO getProductById(int productID);
    public List<ProductDTO> getAllProducts();
}