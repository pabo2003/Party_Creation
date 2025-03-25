package lk.ijse.party_creation.service;

import lk.ijse.party_creation.dto.ProductDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ProductService {
    public int saveProduct(ProductDTO product, MultipartFile file);
    public int deleteProduct(int productID);
    public int updateProduct(ProductDTO productDTO , MultipartFile file);
    public ProductDTO getProductById(int productID);
    public List<ProductDTO> getAllProducts();
}