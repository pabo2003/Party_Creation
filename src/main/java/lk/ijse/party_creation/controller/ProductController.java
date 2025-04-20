package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.CartDTO;
import lk.ijse.party_creation.dto.ProductDTO;
import lk.ijse.party_creation.dto.ResponseDTO;
import lk.ijse.party_creation.entity.Product;
import lk.ijse.party_creation.repo.ProductRepo;
import lk.ijse.party_creation.service.ProductService;
import lk.ijse.party_creation.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;

import static lk.ijse.party_creation.util.VarList.OK;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private final ProductService productService;
    @Autowired
    private final ProductRepo productRepo;


    public ProductController(ProductService productService, ProductRepo productRepo) {
        this.productService = productService;
        this.productRepo = productRepo;
    }

    @PostMapping(value = "/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<ResponseDTO> saveProduct(@RequestPart("productDTO") ProductDTO productDTO, @RequestParam("file") MultipartFile file) {
        try {
            if (!file.isEmpty()) {
                // Save the file to a directory
                Path filePath = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/" + file.getOriginalFilename());
                Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
                System.out.println("File uploaded successfully.");
            }

            int res = productService.saveProduct(productDTO, file);
            switch (res) {
                case VarList.Created:
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(OK, "Product saved successfully", productDTO));
                default:
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDTO(VarList.INTERNAL_SERVER_ERROR, "Failed to save product", null));
            }
        } catch (IOException e) {
            System.out.println("Error uploading file: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Error uploading file", null));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateProduct(@RequestBody ProductDTO productDTO) {
        System.out.println("id: " + productDTO.getProductID());
        try{
            int res = productService.updateProduct(productDTO);

            switch (res) {
                case OK:
                    System.out.println("product updated");
                    return ResponseEntity.ok(new ResponseDTO(OK, "product Updated Successfully", productDTO));
                case VarList.Not_Found:
                    System.out.println("product not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ResponseDTO(VarList.Not_Found, "product Not Found", null));
                default:
                    System.out.println("Error updating product");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDTO(VarList.Internal_Server_Error, "Error updating product", null));
            }
        }catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Internal Server Error", null));
        }
    }

    @DeleteMapping("delete/{productID}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable int productID) {
        int productId = productService.deleteProduct(productID);
        ResponseDTO response = new ResponseDTO(200, "Product delete successfully", productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(
                new ResponseDTO(OK, "List",products)
        );

    }


    @GetMapping("getProductByCode/{productID}")
    public ResponseEntity<ResponseDTO> getProductById(@PathVariable int productID) {
        ProductDTO product = productService.getProductById(productID);
        ResponseDTO response = new ResponseDTO(200, "Product fetched successfully", product);
        return ResponseEntity.ok(response);
    }
    @PostMapping("/getProductById")
    public ResponseEntity<ResponseDTO> getProduct(@RequestBody CartDTO cartDTO) {
        System.out.println("Product get.....................................................................");
        ProductDTO product = productService.getProductById(cartDTO.getProductId());
        return ResponseEntity.ok(
                new ResponseDTO(OK, "List", product)
        );

    }
}