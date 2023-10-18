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
        message.setSubject("Activate your account just by clicking the link below:");
        String emailContent = "<a href='http://127.0.0.1:5173/activate-account?code=" + activationLink + "'>Click here to activate your account</a>";
        message.setText(emailContent);


        javaMailSender.send(message);
    }

}
