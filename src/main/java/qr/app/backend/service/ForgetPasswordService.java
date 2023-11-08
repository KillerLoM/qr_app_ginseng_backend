package qr.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import qr.app.backend.model.User;
import qr.app.backend.repo.UserRepo;
import qr.app.backend.utils.EmailUtil;
import qr.app.backend.utils.OTPUtils;

@Service
public class ForgetPasswordService {
    @Autowired
    private UserRepo adminRepo;
    @Autowired
    private OTPUtils otpUtils;
    @Autowired
    private EmailUtil emailUtil;
    public String forgetPassword(String email){
        User admin1 = adminRepo.findUserByEmail(email);
        if(admin1 == null){
            return "Email not found";
        }
        String otp = otpUtils.generateOtp();
        admin1.setOtp(otp);
        adminRepo.save(admin1);
        String response = emailUtil.sendEmail(email,otp);
        return response;
    }
}
