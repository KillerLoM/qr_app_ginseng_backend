package qr.app.backend.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class EmailSenderService {
    @Value("${spring.mail.username}")
    private String emailSender;
    @Value("${spring.mail.password}")
    private String password;
    @Autowired
    private JavaMailSender mailSender;
    public boolean sendEmail(String email, String subject, String otp) throws MessagingException {
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, "utf-8");
        helper.setTo(email);
        helper.setSubject(subject);
        helper.setFrom(emailSender);
        helper.setText("""
               <div style="font-family: 'Times New Roman', Times, serif;">
               <h1 style="text-align: center; align-items: center;justify-content: center;">
                   THIS IS THE OTP TO RESET YOUR PASSWORD
               </h1>
               <h2>
                   Please do not share this otp to anyone
               </h2>
               <span>
                   Your otp is: 
              
               <p style="font-weight : bold">
                        %s
               </p>
                </span>
               </div>
               """.formatted(otp), true);
        helper.setSentDate(new Date());
        try{
            mailSender.send(message);
        }catch (Exception e){
            throw e;
        }
        return true;
    }
}
