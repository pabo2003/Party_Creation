package lk.ijse.party_creation.service;

import lk.ijse.party_creation.dto.ProductDTO;

import java.util.List;

public interface ProductService {
    public ProductDTO saveProduct(ProductDTO product);
    public int deleteProduct(int productID);
    public ProductDTO updateProduct(int productID, ProductDTO productDTO);
    public ProductDTO getProductById(int productID);
    public List<ProductDTO> getAllProducts();
}