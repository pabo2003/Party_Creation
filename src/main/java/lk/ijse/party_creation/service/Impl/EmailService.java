package lk.ijse.party_creation.service.Impl;

import lk.ijse.party_creation.entity.Order;
import lk.ijse.party_creation.entity.OrderDetail;
import lk.ijse.party_creation.entity.Payment;
import lk.ijse.party_creation.entity.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void sendVerificationEmail(String email, String code) {
        try{
            System.out.println("Sending verification email to: " + email);
            System.out.println("Main Sending");
            System.out.println("code verify "+code);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(email);
            message.setFrom("sandunikhitigama@gmail.com");
            message.setSubject("Your Email Verification Code");
            message.setText("Your verification code is: " + code);
            mailSender.send(message);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public void sendSlip(Order order, List<OrderDetail> orderDetails, Payment payment) {
        try {
            String toEmail = order.getUser().getEmail();
            int orderId = order.getOrderID();
            String fullName = order.getFullName();
            String address = order.getAddress();
            String city = order.getCity();
            String zipCode = order.getZipCode();
            Date orderDate = order.getOrderDate();
            double totalAmount = order.getTotalAmount();

            StringBuilder emailContent = new StringBuilder();
            emailContent.append("Order Confirmation\n");
            emailContent.append("==================\n\n");
            emailContent.append("Thank you for your order!\n\n");
            emailContent.append("Order Details:\n");
            emailContent.append("-------------\n");
            emailContent.append("Order Number: ").append(orderId).append("\n");
            emailContent.append("Order Date: ").append(orderDate).append("\n");
            emailContent.append("Customer: ").append(fullName).append("\n");
            emailContent.append("Delivery Address: ").append(address).append(", ")
                    .append(city).append(", ").append(zipCode).append("\n\n");

            emailContent.append("Items Ordered:\n");
            emailContent.append("-------------\n");

            for (OrderDetail item : orderDetails) {
                emailContent.append(item.getProduct().getName())
                        .append(" - Quantity: ").append(item.getQuantity())
                        .append(" - Price: $").append(String.format("%.2f", item.getPrice()))
                        .append("\n");
            }

            emailContent.append("\nPayment Information:\n");
            emailContent.append("-------------------\n");
            emailContent.append("Payment Method: ").append(payment.getPaymentMethod()).append("\n");
            emailContent.append("Total Amount: $").append(String.format("%.2f", totalAmount)).append("\n\n");

            emailContent.append("If you have any questions about your order, please contact our support team.\n\n");
            emailContent.append("Thank you for shopping with us!\n");

            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setFrom("sandunikhitigama@gmail.com");
            message.setSubject("Your Order Confirmation #" + orderId);
            message.setText(emailContent.toString());

            mailSender.send(message);

        } catch (Exception e) {
            throw new RuntimeException("Failed to send order confirmation email", e);
        }
    }

}
