package lk.ijse.party_creation.service.Impl;
import lk.ijse.party_creation.dto.OrderDTO;
import lk.ijse.party_creation.entity.Customization;
import lk.ijse.party_creation.entity.Order;
import lk.ijse.party_creation.entity.User;
import lk.ijse.party_creation.repo.CustomizationRepo;
import lk.ijse.party_creation.repo.OrderRepo;
import lk.ijse.party_creation.service.OrderService;
import lk.ijse.party_creation.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepo orderRepo;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private CustomizationRepo customizationRepo;
    @Override
    public int saveOrder(OrderDTO orderDTO) {
        try{
            Order order = new Order();
            order.setOrderID(orderDTO.getOrderID());
            Customization customization = customizationRepo.findByEmail(String.valueOf(orderDTO.getCustomId()));
            order.setCustomization(customization);
            order.setStatus(orderDTO.getStatus());
            order.setOrderDate(String.valueOf(orderDTO.getOrderDate()));
            order.setTotalAmount(orderDTO.getTotalAmount());

            orderRepo.save(order);
            return VarList.Created;
        }catch (Exception e){
            throw new RuntimeException("Failed to save order: " + e.getMessage(), e);
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
        try {
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
        }
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
