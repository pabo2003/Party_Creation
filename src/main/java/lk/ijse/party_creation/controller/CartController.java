package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.CartDTO;
import lk.ijse.party_creation.dto.ResponseDTO;
import lk.ijse.party_creation.entity.Cart;
import lk.ijse.party_creation.repo.CartRepo;
import lk.ijse.party_creation.service.CartService;
import lk.ijse.party_creation.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static lk.ijse.party_creation.util.VarList.Created;
import static lk.ijse.party_creation.util.VarList.OK;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/v1/cart")
public class CartController {
@Autowired
    private final CartService cartService;
@Autowired
    private final CartRepo cartRepo;

    public CartController(CartService cartService, CartRepo cartRepo) {
        this.cartService = cartService;
        this.cartRepo = cartRepo;
    }

    @PostMapping(value = "/cartSave")
    public ResponseEntity<ResponseDTO> saveProduct(@RequestBody CartDTO cartDTO) {
        try {
            System.out.println(cartDTO);
            System.out.println("cart save.....................................................................");
            int res = cartService.saveCart(cartDTO);
            switch (res) {
                case VarList.Created:
                    return ResponseEntity.status(HttpStatus.CREATED)
                            .body(new ResponseDTO(Created, "cart saved successfully", cartDTO));
                default:
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDTO(VarList.INTERNAL_SERVER_ERROR, "Failed to save cart", null));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping(value = "/cartDelete")
    public ResponseEntity<ResponseDTO> deleteProduct(@RequestBody CartDTO cartDTO) {
        try {
            System.out.println("cart delete.....................................................................");
            System.out.println(cartDTO);
            int res = cartService.delete(cartDTO);
            switch (res) {
                case VarList.OK:
                    return ResponseEntity.status(HttpStatus.OK)
                            .body(new ResponseDTO(OK, "cart delete successfully", cartDTO));
                default:
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDTO(VarList.INTERNAL_SERVER_ERROR, "Failed to delete cart", null));
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @PostMapping("/getAll")
    public ResponseEntity<ResponseDTO> getAll(@RequestBody CartDTO cartDTO) {
        System.out.println("cart getAll.....................................................................");
        List<Cart> cart = cartRepo.findByEmail(cartDTO.getEmail());
        return ResponseEntity.ok(
                new ResponseDTO(OK, "List", cart)
        );

    }
}
