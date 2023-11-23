package qr.app.backend.utils;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Random;
@Component
public class OTPUtils {
    public String generateOtp(){
        Random random = new Random();
        int temp = random.nextInt(9999);
        String output = Integer.toString(temp);
        while(output.length()<4){
            output = "0" + output;
        }
        return  output;
    }
}
