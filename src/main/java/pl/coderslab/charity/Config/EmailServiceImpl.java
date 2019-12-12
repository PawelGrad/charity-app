package pl.coderslab.charity.Config;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailServiceImpl {


    private JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void sendSimpleMessage() {

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo("gradpawel91@gmail.com");
        message.setSubject("test");
        message.setText("HURR");
        emailSender.send(message);

    }
}