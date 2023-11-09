package qr.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import qr.app.backend.config.PasswordEncode;
import qr.app.backend.model.User;
import qr.app.backend.repo.UserRepo;
import qr.app.backend.utils.EmailUtil;
import qr.app.backend.utils.JwtUtils;
import qr.app.backend.utils.OTPUtils;

import java.security.SecureRandom;
import java.time.Instant;
import java.util.Base64;
import java.util.Objects;

@Service
public class ForgetPasswordService {
    Instant now = Instant.now();
    @Autowired
    private UserRepo adminRepo;
    @Autowired
    private OTPUtils otpUtils;
    @Autowired
    private EmailUtil emailUtil;
    @Autowired
    private BCryptPasswordEncoder passwordEncode;
    public String forgetPassword(String email) throws Exception{
        User admin1 = adminRepo.findUserByEmail(email);
        if(admin1 == null){
            throw new Exception( "Email not found");
        }
        String otp = otpUtils.generateOtp();
        String otpHashed = passwordEncode.encode(otp);
        admin1.setOtp(otpHashed);

        long secondsEpoch = now.getEpochSecond();
        admin1.setExpiration_otp(secondsEpoch);
        adminRepo.save(admin1);
        String response;
        try{
            response = emailUtil.sendEmail(email,otp);
        }catch (Exception e){
            throw e;
        }
        return response + " voi otp la: " + otp;
    }
    public String validateOtp(String email, String otp) throws  Exception{
        User admin1 = adminRepo.findUserByEmail(email);
        boolean check = new BCryptPasswordEncoder().matches(otp, admin1.getOtp());
        if(check){
            if(now.getEpochSecond() - admin1.getExpiration_otp() < 60){
                String key = generateRandomAPIKey();
                String keyHashed = passwordEncode.encode(key.substring(0, key.length()-1));
                admin1.setKey_otp(keyHashed);
                admin1.setOtp("");
                admin1.setExpiration_otp(null);
                adminRepo.save(admin1);
                return key;
            }
            else{
                admin1.setOtp("");
                admin1.setExpiration_otp(null);
                throw  new Exception("otp has been expired. Please send again!") ;
            }
        }
        else{
            admin1.setOtp("");
            admin1.setExpiration_otp(null);
          throw new Exception("OTP is not valid!");
        }
    }
    public String changePassword(String key, String email, String password) throws Exception {
        User admin = adminRepo.findUserByEmail(email);
        if(new BCryptPasswordEncoder().matches(key, admin.getKey_otp())){
            System.out.println(admin.getKey_otp());
            throw new Exception("Invalid statement");
        }
        String passwordHashed = passwordEncode.encode(password);
        admin.setPassword(passwordHashed);
        admin.setKey_otp("");
        adminRepo.save(admin);
        return "Success";
    }
    private static String generateRandomAPIKey() {
        SecureRandom random = new SecureRandom();
        byte[] keyBytes = new byte[32];
        random.nextBytes(keyBytes);
        return Base64.getEncoder().encodeToString(keyBytes);
    }
}
