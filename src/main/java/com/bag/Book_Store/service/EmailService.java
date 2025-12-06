package com.bag.Book_Store.service;

public interface EmailService {
    Boolean sendEmail(String toEmail, String subject, String body);
    Boolean sendOrderConfirmationEmail(String toEmail,
                                       String orderNumber,
                                       Double totalAmount,
                                       String fullName);
}
