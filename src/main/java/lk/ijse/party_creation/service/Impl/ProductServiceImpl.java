package lk.ijse.party_creation.service.Impl;

import jakarta.transaction.Transactional;
import lk.ijse.party_creation.dto.ProductDTO;
import lk.ijse.party_creation.entity.Product;
import lk.ijse.party_creation.repo.ProductRepo;
import lk.ijse.party_creation.repo.UserRepo;
import lk.ijse.party_creation.service.ProductService;
import lk.ijse.party_creation.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepo userRepo;


    @Override
    public int saveProduct(ProductDTO productDTO, MultipartFile file) {
        try {
            Product product = new Product();
            if (file != null && !file.isEmpty()) {
                product.setFileName(file.getOriginalFilename());
                product.setFiletype(file.getContentType());
                product.setData(file.getBytes());
            }
            product.setProductID(productDTO.getProductID());
            product.setName(productDTO.getName());
            product.setPrice(productDTO.getPrice());
            product.setStock(productDTO.getStock());
            product.setCategory(productDTO.getCategory());
            product.setDescription(productDTO.getDescription());
            productRepo.save(product);



            return VarList.Created;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save product: " + e.getMessage(), e);
        }
    }

    @Override
    public int deleteProduct(int productID) {
        if (userRepo.existsById(String.valueOf(productID))){
            productRepo.deleteById(productID);
            return VarList.OK;
        }else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int updateProduct( ProductDTO productDTO ,MultipartFile file) {
        /*Product existingProduct = productRepo.findById(productID)
                .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productID));
        modelMapper.map(productDTO, existingProduct);
        Product updatedProduct = productRepo.save(existingProduct);
        return modelMapper.map(updatedProduct, ProductDTO.class);*/
        try{
            if(productRepo.existsById(productDTO.getProductID())) {
                Product product = productRepo.findById(productDTO.getProductID())
                        .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productDTO.getProductID()));
                if (file != null && !file.isEmpty()) {
                    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
                    product.setFileName(fileName);
                    product.setFiletype(file.getContentType());
                    product.setData(file.getBytes());
                }

                product.setName(productDTO.getName());
                product.setPrice(productDTO.getPrice());
                product.setStock(productDTO.getStock());
                product.setCategory(productDTO.getCategory());
                product.setDescription(productDTO.getDescription());

                productRepo.save(product);
                return VarList.OK;
            }else {
                return VarList.Not_Found;
            }
        }catch (Exception e){
            throw new RuntimeException("Failed to update product: " + e.getMessage(), e);
        }
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