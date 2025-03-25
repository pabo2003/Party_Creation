/*
package lk.ijse.party_creation.controller;

import lk.ijse.party_creation.dto.PaymentDTO;
import lk.ijse.party_creation.service.PaymentService;
import lk.ijse.party_creation.util.VarList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/payments")
@CrossOrigin("*")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("/savePayment")
    public ResponseEntity<String> savePayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            int status = paymentService.savePayment(paymentDTO);
            if (status == VarList.Created) {
                return new ResponseEntity<>("Payment saved successfully", HttpStatus.CREATED);
            }
            return new ResponseEntity<>("Failed to save payment", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error saving payment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{paymentID}")
    public ResponseEntity<String> deletePayment(@PathVariable int paymentID) {
        try {
            int status = paymentService.deletePayment(paymentID);
            if (status == VarList.OK) {
                return new ResponseEntity<>("Payment deleted successfully", HttpStatus.OK);
            } else {
                return new ResponseEntity<>("Payment not found", HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting payment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<String> updatePayment(@RequestBody PaymentDTO paymentDTO) {
        try {
            int status = paymentService.updatePayment(paymentDTO);
            if (status == VarList.OK) {
                return new ResponseEntity<>("Payment updated successfully", HttpStatus.OK);
            }
            return new ResponseEntity<>("Failed to update payment", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating payment: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{paymentID}")
    public ResponseEntity<PaymentDTO> getPaymentById(@PathVariable int paymentID) {
        try {
            PaymentDTO paymentDTO = paymentService.getPaymentById(paymentID);
            if (paymentDTO != null) {
                return new ResponseEntity<>(paymentDTO, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<PaymentDTO>> getAllPayments() {
        try {
            List<PaymentDTO> paymentDTOs = paymentService.getAllPayments();
            if (!paymentDTOs.isEmpty()) {
                return new ResponseEntity<>(paymentDTOs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/{userID}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByUserId(@PathVariable int userID) {
        try {
            List<PaymentDTO> paymentDTOs = paymentService.getPaymentsByUserId(userID);
            if (!paymentDTOs.isEmpty()) {
                return new ResponseEntity<>(paymentDTOs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/order/{orderID}")
    public ResponseEntity<List<PaymentDTO>> getPaymentsByOrderId(@PathVariable int orderID) {
        try {
            List<PaymentDTO> paymentDTOs = paymentService.getPaymentsByOrderId(orderID);
            if (!paymentDTOs.isEmpty()) {
                return new ResponseEntity<>(paymentDTOs, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
*/
