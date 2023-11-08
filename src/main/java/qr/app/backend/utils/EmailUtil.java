package qr.app.backend.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import qr.app.backend.service.EmailSenderService;

@Component
public class EmailUtil {
    @Autowired
    private EmailSenderService emailService;
    public String sendEmail(String email, String otp){
       String subject = "Verify your account";
       String code = """
               <div style="font-family: 'Times New Roman', Times, serif;">
               <h1 style="text-align: center; align-items: center;justify-content: center;">
                   THIS IS THE OTP TO RESET YOUR PASSWORD
               </h1>
               <h2>
                   Please do not share this otp to anyone
               </h2>
               <span>
                   Your otp is:\s %s
               </span>
               </div>
               """ .formatted(otp);
       try{
           emailService.sendEmail(email,subject,otp);
       }catch(Exception e){
           return "Had problems while sending mail ! " + e.getMessage();

       }
        return "Email has been sent";
    }
}
