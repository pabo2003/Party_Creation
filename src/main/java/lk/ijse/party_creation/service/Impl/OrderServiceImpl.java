package lk.ijse.party_creation.service.Impl;
import lk.ijse.party_creation.dto.OrderDTO;
import lk.ijse.party_creation.dto.OrderDetailsDTO;
import lk.ijse.party_creation.dto.TransactionDTO;
import lk.ijse.party_creation.entity.Order;
import lk.ijse.party_creation.entity.OrderDetail;
import lk.ijse.party_creation.entity.Payment;
import lk.ijse.party_creation.entity.Product;
import lk.ijse.party_creation.repo.OrderDetailRepo;
import lk.ijse.party_creation.repo.OrderRepo;
import lk.ijse.party_creation.repo.PaymentRepo;
import lk.ijse.party_creation.repo.ProductRepo;
import lk.ijse.party_creation.service.OrderService;
import lk.ijse.party_creation.service.ProductService;
import lk.ijse.party_creation.util.VarList;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private OrderDetailRepo orderDetailRepo;
    @Autowired
    private PaymentRepo paymentRepo;
    @Autowired
    private EmailService emailService;
    @Autowired
    private ProductRepo productRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Override
    public int saveOrder(TransactionDTO transactionDTO) {
        try {
            Order order = modelMapper.map(transactionDTO.getOrderDTO(), Order.class);
            Order savedOrder = orderRepo.save(order);

            if (savedOrder != null && transactionDTO.getOdList() != null) {
                List<OrderDetail> orderDetails = modelMapper.map(
                        transactionDTO.getOdList(),
                        new TypeToken<ArrayList<OrderDetail>>(){}.getType()
                );

                for (OrderDetail orderDetail : orderDetails) {
                    orderDetail.setOrder(savedOrder);
                    OrderDetail savedOrderDetail = orderDetailRepo.save(orderDetail);

                    int productID = savedOrderDetail.getProduct().getProductID();
                    Product product = productRepo.findById(productID)
                            .orElseThrow(() -> new RuntimeException("Product not found with ID: " + productID));
                    int pastStock = product.getStock();
                    int qty = savedOrderDetail.getQuantity();
                    int newStock = pastStock - qty;
                    product.setStock(newStock);
                    productRepo.save(product);

                    if (transactionDTO.getPaymentDTO() != null) {
                        Payment payment = modelMapper.map(transactionDTO.getPaymentDTO(), Payment.class);
                        payment.setOrderDetail(savedOrderDetail);
                        paymentRepo.save(payment);
                        emailService.sendSlip(savedOrder, orderDetails, payment);
                    }
                }
                return VarList.OK;
            }
            return VarList.Not_Found;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save order", e);
        }
    }

    @Override
    public int deleteOrder(int orderID) {
        if(orderRepo.existsById(orderID)){
            orderRepo.deleteById(orderID);
            return VarList.OK;
        }else {
            return VarList.Not_Found;
        }
    }

    @Override
    public int updateOrder(OrderDTO orderDTO) {
       /* try {
            Order order = orderRepo.findByOrderID(orderDTO.getOrderID());
            order.setOrderDate(String.valueOf(orderDTO.getOrderDate()));
            order.setTotalAmount(orderDTO.getTotalAmount());
            order.setStatus(orderDTO.getStatus());

            Customization customization = customizationRepo.findByEmail(String.valueOf(orderDTO.getCustomId()));
            order.setCustomization(customization);

            orderRepo.save(order);
            return VarList.OK;
        }catch (Exception e){
            throw new RuntimeException("Failed to update order: "+e.getMessage());
        }*/
        return 0;
    }

    @Override
    public OrderDTO getOrderById(int orderID) {
        return null;
    }

    @Override
    public List<OrderDTO> getAllOrders() {
        return List.of();
    }

    @Override
    public List<OrderDTO> getOrdersByCustomer(int customID) {
        return List.of();
    }

    @Override
    public List<OrderDTO> getOrdersByStatus(String status) {
        return List.of();
    }
}
