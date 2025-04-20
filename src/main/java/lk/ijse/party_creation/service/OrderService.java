package lk.ijse.party_creation.service;

import lk.ijse.party_creation.dto.OrderDTO;
import lk.ijse.party_creation.dto.TransactionDTO;

import java.util.List;

public interface OrderService {
    int saveOrder(TransactionDTO transactionDTO);
    int deleteOrder(int orderID);
    int updateOrder(OrderDTO orderDTO);
    OrderDTO getOrderById(int orderID);
    List<OrderDTO> getAllOrders();
    List<OrderDTO> getOrdersByCustomer(int customID);
    List<OrderDTO> getOrdersByStatus(String status);
}
