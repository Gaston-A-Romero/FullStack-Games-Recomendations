package com.Reviews.Security.Auth;
import com.Reviews.Security.User.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailAuthService {
    @Autowired
    private JavaMailSender javaMailSender;
    //NEEDS TO BE CHANGED
    public void sendActivationEmail(User user, String activationLink) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(user.getEmail());
        message.setSubject("Activate your account");
        message.setText("make post request on MAPPING with the param activation link:\n" + activationLink);

        javaMailSender.send(message);
    }

}
