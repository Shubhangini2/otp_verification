package com.otpAuthentication.Service;

import org.springframework.beans.factory.annotation.Autowired;


import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;




@Service
public class EmailService {

    @Autowired
    JavaMailSender javaMailSender;

    public void sendEmail(String to, String subject, String text) {

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(to);
//        mail.setFrom("gryffindor894@gmail.com");
        mail.setSubject(subject);
        mail.setText(text);
        javaMailSender.send(mail);
    }
}

//        String text = "Congrats! Your order has been placed. Following are the details: '\n' " +
//                "Order id = "+ order.getOrderId() + "\n"
//                + "Order total = " + order.getOrderTotal()
//                + "Order Date = " + order.getOrderDate();

//        try {
//            SimpleMailMessage mail = new SimpleMailMessage();
//            mail.setTo(to);
//        mail.setFrom("gryffindor894@gmail.com");
//            mail.setSubject(subject);
//            mail.setText(text);
//            javaMailSender.send(mail);
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }

//    }
//}