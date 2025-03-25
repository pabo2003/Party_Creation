/*
package lk.ijse.party_creation.service.Impl;

import lk.ijse.party_creation.dto.PaymentDTO;
import lk.ijse.party_creation.entity.Payment;
import lk.ijse.party_creation.repo.PaymentRepo;
import lk.ijse.party_creation.service.PaymentService;
import lk.ijse.party_creation.util.VarList;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentRepo paymentRepo; // Payment Repository

    @Autowired
    private ModelMapper modelMapper; // ModelMapper for DTO and Entity mapping

    @Override
    public int savePayment(PaymentDTO paymentDTO) {
        try {
            // Convert PaymentDTO to Payment entity
            Payment payment = modelMapper.map(paymentDTO, Payment.class);

            // Save the payment entity to the database
            paymentRepo.save(payment);

            // Return success status
            return VarList.Created;
        } catch (Exception e) {
            throw new RuntimeException("Failed to save payment: " + e.getMessage(), e);
        }
    }

    @Override
    public int deletePayment(int paymentID) {
        try {
            // Check if the payment exists
            if (paymentRepo.existsById(paymentID)) {
                // Delete the payment by ID
                paymentRepo.deleteById(paymentID);
                return VarList.OK; // Return success status
            } else {
                return VarList.Not_Found; // Return "Not Found" status if payment doesn't exist
            }
        } catch (Exception e) {
            throw new RuntimeException("Failed to delete payment: " + e.getMessage(), e);
        }
    }

    @Override
    public int updatePayment(PaymentDTO paymentDTO) {
        try {
            // Find existing payment by ID
            Payment payment = paymentRepo.findById(paymentDTO.getPaymentID())
                    .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentDTO.getPaymentID()));

            // Update the payment details
            payment.setOrderID(paymentDTO.getOrderID());
            payment.setUserID(paymentDTO.getUserID());
            payment.setPaymentMethod(paymentDTO.getPaymentMethod());
            payment.setAmount(paymentDTO.getAmount());

            // Save the updated payment
            paymentRepo.save(payment);

            return VarList.OK; // Return success status
        } catch (Exception e) {
            throw new RuntimeException("Failed to update payment: " + e.getMessage(), e);
        }
    }

    @Override
    public PaymentDTO getPaymentById(int paymentID) {
        try {
            // Find the payment by ID
            Payment payment = paymentRepo.findById(paymentID)
                    .orElseThrow(() -> new RuntimeException("Payment not found with ID: " + paymentID));

            // Convert Payment entity to PaymentDTO
            return modelMapper.map(payment, PaymentDTO.class);
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve payment: " + e.getMessage(), e);
        }
    }

    @Override
    public List<PaymentDTO> getAllPayments() {
        try {
            // Fetch all payments from the repository
            List<Payment> payments = paymentRepo.findAll();

            // Convert the list of Payment entities to PaymentDTOs
            return payments.stream()
                    .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                    .collect(java.util.stream.Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve all payments: " + e.getMessage(), e);
        }
    }

    @Override
    public List<PaymentDTO> getPaymentsByUserId(int userID) {
        try {
            // Find all payments by user ID
            List<Payment> payments = paymentRepo.findByUserID(userID);

            // Convert the list of Payment entities to PaymentDTOs
            return payments.stream()
                    .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                    .collect(java.util.stream.Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve payments by user ID: " + e.getMessage(), e);
        }
    }

    @Override
    public List<PaymentDTO> getPaymentsByOrderId(int orderID) {
        try {
            // Find all payments by order ID
            List<Payment> payments = (List<Payment>) paymentRepo.findByOrderID(orderID);

            // Convert the list of Payment entities to PaymentDTOs
            return payments.stream()
                    .map(payment -> modelMapper.map(payment, PaymentDTO.class))
                    .collect(java.util.stream.Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException("Failed to retrieve payments by order ID: " + e.getMessage(), e);
        }
    }
}
*/
