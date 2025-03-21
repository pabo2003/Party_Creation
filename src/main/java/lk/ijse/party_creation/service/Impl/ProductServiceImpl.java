package lk.ijse.party_creation.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.party_creation.dto.ProductDTO;
import lk.ijse.party_creation.entity.Product;
import lk.ijse.party_creation.repo.ProductRepo;
import lk.ijse.party_creation.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;


    @Override
    public ProductDTO saveProduct(ProductDTO productDTO) {
        Product product = modelMapper.map(productDTO, Product.class);
        Product savedProduct = productRepo.save(product);
        return modelMapper.map(savedProduct, ProductDTO.class);
    }

    @Override
    public int deleteProduct(int productID) {
        productRepo.deleteById(productID);
        return productID;
    }

    @Override
    public ProductDTO updateProduct(int productID, ProductDTO productDTO) {
        Product existingProduct = productRepo.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productID));
        modelMapper.map(productDTO, existingProduct);
        Product updatedProduct = productRepo.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductDTO.class);
    }

    @Override
    public ProductDTO getProductById(int productID) {
        Product product = productRepo.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productID));
        return modelMapper.map(product, ProductDTO.class);
    }

    @Override
    public List<ProductDTO> getAllProducts() {
        List<Product> products = productRepo.findAll();
        return products.stream()
                .map(product -> modelMapper.map(product, ProductDTO.class))
                .toList();
    }
}
