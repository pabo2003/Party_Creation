package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.OrderDTO;
import lk.ijse.party_creation.dto.ResponseDTO;
import lk.ijse.party_creation.service.OrderService;
import lk.ijse.party_creation.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/v1/orders")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
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
    public ResponseEntity<ResponseDTO> saveOrderInfo(@RequestBody OrderDTO orderDTO){

        System.out.println(orderDTO);
        try{
            Date localDate = java.sql.Date.valueOf(LocalDate.now());
            orderDTO.setOrderDate(localDate);
            int res = orderService.saveOrder(orderDTO);

            switch (res){
                case VarList.Created:
                    System.out.println("Order saved successfully");
                    return ResponseEntity.ok(new ResponseDTO(VarList.OK,"Order information saved successfully",null));
                case VarList.Not_Acceptable:
                    System.out.println("Order already exists");
                    return ResponseEntity.ok(new ResponseDTO(VarList.Not_Acceptable,"Id already exists",null));
                default:
                    System.out.println("Error saving Order");
                    return ResponseEntity.ok(new ResponseDTO(VarList.INTERNAL_SERVER_ERROR,"An error occurred",null));
            }
        }catch (Exception e){
            throw new RuntimeException(e);
        }
    }

    @PutMapping(value = "/update")
    public ResponseEntity<ResponseDTO> updateOrder(@RequestBody OrderDTO orderDTO) {
        System.out.println("id: " + orderDTO.getOrderID());

        try{
            int res = orderService.updateOrder(orderDTO);

            switch (res) {
                case VarList.OK:
                    System.out.println("Order updated");
                    return ResponseEntity.ok(new ResponseDTO(VarList.OK, "Order Updated Successfully", null));
                case VarList.Not_Found:
                    System.out.println("Order not found");
                    return ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(new ResponseDTO(VarList.Not_Found, "Order Not Found", null));
                default:
                    System.out.println("Error updating Order");
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(new ResponseDTO(VarList.Internal_Server_Error, "Error updating Order", null));
            }
        }catch (Exception e) {
            System.out.println("Exception occurred: " + e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new ResponseDTO(VarList.Internal_Server_Error, "Internal Server Error", null));
        }
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<ResponseDTO> deleteOrder(@PathVariable int orderId) {
        int order = orderService.deleteOrder(Integer.parseInt(String.valueOf(orderId)));
        ResponseDTO response = new ResponseDTO(200, "customer delete successfully", order);
        return ResponseEntity.ok(response);
    }

    @GetMapping("getAll")
    public ResponseEntity<ResponseDTO> getAllOrders() {
        List<OrderDTO> orders = orderService.getAllOrders();
        ResponseDTO response = new ResponseDTO(200, "All customer fetched successfully", orders);
        return ResponseEntity.ok(response);
    }
}
