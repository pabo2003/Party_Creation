package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.ProductDTO;
import lk.ijse.party_creation.dto.ResponseDTO;
import lk.ijse.party_creation.service.ProductService;
import lk.ijse.party_creation.util.ResponseUtil;
import lk.ijse.party_creation.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping(value = "/save")
    public ResponseEntity<ResponseDTO> saveProduct(@RequestBody ProductDTO productDTO) {
        ResponseDTO response = null;
        try {
            ProductDTO saveProduct = productService.saveProduct(productDTO);
            ResponseDTO responseDTO = new ResponseDTO(200, "Product saved successfully", saveProduct);
            return ResponseEntity.ok(responseDTO);
        }catch (Exception e) {
            ResponseDTO responseDTO = new ResponseDTO(500, "An error occurred: " + e.getMessage(), null);
            return ResponseEntity.status(500).body(responseDTO);
        }
    }

    @PutMapping(value = "update")
    public ResponseEntity<ResponseDTO> updateProduct(@PathVariable int productID,@RequestBody ProductDTO productDTO) {
        ProductDTO updatedProduct = productService.updateProduct(productID, productDTO);
        ResponseDTO response = new ResponseDTO(200, "Product updated successfully", updatedProduct);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("delete/{productID}")
    public ResponseEntity<ResponseDTO> deleteProduct(@PathVariable int productID) {
        int productId = productService.deleteProduct(productID);
        ResponseDTO response = new ResponseDTO(200, "Product delete successfully", productId);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getAll")
    public ResponseEntity<ResponseDTO> getAllProducts() {
        List<ProductDTO> products = productService.getAllProducts();
        ResponseDTO response = new ResponseDTO(200, "All products fetched successfully", products);
        return ResponseEntity.ok(response);
    }

    /*@GetMapping("getProductIds")
    public ResponseUtil getProductIds() {
        return new ResponseUtil(200,"Get All Item Codes",productService.getProductIds());
    }*/


    @GetMapping("getItemByCode/{productID}")
    public ResponseEntity<ResponseDTO> getProductById(@PathVariable int productID) {
        ProductDTO product = productService.getProductById(productID);
        ResponseDTO response = new ResponseDTO(200, "Product fetched successfully", product);
        return ResponseEntity.ok(response);
    }
}