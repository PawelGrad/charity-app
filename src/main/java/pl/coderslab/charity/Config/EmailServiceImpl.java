package pl.coderslab.charity.Config;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class EmailServiceImpl {


    private JavaMailSender emailSender;

    public EmailServiceImpl(JavaMailSender emailSender) {
        this.emailSender = emailSender;
    }

    public void accountActivationEmail(String email, String uuid){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("AccountActivation");
        message.setText("http://localhost:8080/activate/" + uuid);
        emailSender.send(message);

    }

    public void passwordRecoveryEmail(String email, String uuid){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(email);
        message.setSubject("PasswordRestoration");
        message.setText("http://localhost:8080/changePassword/" + uuid);
        emailSender.send(message);

    }
}