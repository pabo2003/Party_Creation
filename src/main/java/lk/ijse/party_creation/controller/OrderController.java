package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.*;
import lk.ijse.party_creation.entity.Cart;
import lk.ijse.party_creation.repo.CartRepo;
import lk.ijse.party_creation.service.OrderService;
import lk.ijse.party_creation.service.ProductService;
import lk.ijse.party_creation.service.UserService;
import lk.ijse.party_creation.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static lk.ijse.party_creation.util.VarList.OK;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private final OrderService orderService;
    @Autowired
    private final UserService userService;
    @Autowired
    private final ProductService productService;
    @Autowired
    private final CartRepo cartRepo;

    public OrderController(OrderService orderService, UserService userService, ProductService productService, CartRepo cartRepo) {
        this.orderService = orderService;
        this.userService = userService;
        this.productService = productService;
        this.cartRepo = cartRepo;
    }

    @GetMapping(value = "/getOrderInfo")
    public ResponseEntity<ResponseDTO> getOrderByOrderId(@RequestParam String orderId){
        OrderDTO orderDTO = orderService.getOrderById(Integer.parseInt(orderId));

        if (orderDTO == null){
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Can't find order",null));
        }else{
            return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Success",orderDTO));
        }
    }

    @PostMapping(value = "/saveOrderInfo")
    public ResponseEntity<ResponseDTO> saveOrderInfo(@RequestBody Transaction2DTO transactionDTO){
        System.out.println("transaction .....................");
        double totalAmount = transactionDTO.getTotalAmount();
        String fullName = transactionDTO.getFullName();
        String email = transactionDTO.getEmail();
        String userEmail = transactionDTO.getUserEmail();
        String address = transactionDTO.getAddress();
        String city = transactionDTO.getCity();
        String zipCode = transactionDTO.getZipCode();
        Date date = Date.valueOf(LocalDate.now());
        UserDTO userDTO = userService.searchUser(userEmail);

        List<Cart> cart = cartRepo.findByEmail(userEmail);
        List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>();
        for (Cart cart1 : cart){
            OrderDetailsDTO orderDetailsDTO = new OrderDetailsDTO();

            int productId = cart1.getProductId();
            int qty = cart1.getQuantity();
            ProductDTO productDTO = productService.getProductById(productId);
            double price = productDTO.getPrice();
            double price2 = qty * price;
            System.out.println("ordeeeeeeeeeer"+price2);

            orderDetailsDTO.setProduct(productDTO);
            orderDetailsDTO.setQuantity(qty);
            orderDetailsDTO.setPrice(price2);

            orderDetailsDTOS.add(orderDetailsDTO);
        }



        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setUser(userDTO);
        orderDTO.setOrderDate(date);
        orderDTO.setTotalAmount(totalAmount);
        orderDTO.setStatus("Complete");
        orderDTO.setFullName(fullName);
        orderDTO.setAddress(address);
        orderDTO.setCity(city);
        orderDTO.setZipCode(zipCode);
        orderDTO.setEmail(email);
        System.out.println("Order dtoo  55"+orderDTO);

        PaymentDTO paymentDTO = new PaymentDTO();
        paymentDTO.setAmount(totalAmount);
        paymentDTO.setPaymentMethod("Bank Transfer");
        paymentDTO.setUserEmail(userEmail);
        System.out.println("Order dtoo 333333"+paymentDTO);


        TransactionDTO transactionDTO1 = new TransactionDTO();
        transactionDTO1.setOrderDTO(orderDTO);
        transactionDTO1.setOdList(orderDetailsDTOS);
        transactionDTO1.setPaymentDTO(paymentDTO);

        int res = orderService.saveOrder(transactionDTO1);

        switch (res){
            case OK:
                System.out.println("Transaction save");
                List<Cart> cart1 = cartRepo.findByEmail(userEmail);
                for (Cart cart2 : cart1){
                    cartRepo.delete(cart2);
                }
                return ResponseEntity.ok(new ResponseDTO(OK, "Successfully", transactionDTO));
            case VarList.Not_Found:
                System.out.println("not found");
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new ResponseDTO(VarList.Not_Found, " Not Found", null));
            default:
                System.out.println("Error updating product");
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new ResponseDTO(VarList.Internal_Server_Error, "Error updating product", null));
        }

    }



    @GetMapping("getAll")
    public ResponseEntity<ResponseDTO> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        ResponseDTO response = new ResponseDTO(200, "All customer fetched successfully", orders);
        return ResponseEntity.ok(response);
    }
}
