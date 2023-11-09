package qr.app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qr.app.backend.config.PasswordEncode;
import qr.app.backend.dto.UserDto;
import qr.app.backend.model.User;
import qr.app.backend.repo.UserRepo;
import qr.app.backend.dto.LoginDto;
import qr.app.backend.dto.ValidateDto;
import qr.app.backend.response.LoginResponse;
import qr.app.backend.service.ForgetPasswordService;
import qr.app.backend.utils.JwtUtils;

@Controller
@RequestMapping("/admin/authenticate")
public class AuthController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncode passwordEncode;
    @Autowired
    private ForgetPasswordService forgetPasswordService;
    @CrossOrigin(origins = "*")
    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> loginHandle(@RequestBody LoginDto request){
        String password = request.getPassword();
        String email = request.getEmail();
        User admin = userRepo.findUserByEmail(email);
        if(admin == null){
            return new ResponseEntity<>(LoginResponse.fail("Admin not found"), HttpStatus.UNAUTHORIZED);
        }
        if(new BCryptPasswordEncoder().matches(password, admin.getPassword())){

            LoginResponse response = new LoginResponse(email, true, jwtUtils.generateToken(email, admin.getRole()),"ok");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(LoginResponse.fail("PASSWORD IS NOT CORRECT"), HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/validate-token")
    public ResponseEntity<?> validateToken(@RequestBody ValidateDto request){
        try{
             jwtUtils.validateToken(request.getToken());
        } catch (Exception e){
            return new ResponseEntity<>( e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }
    @PostMapping("/forget-password")
    public ResponseEntity<?> verifyAccount(@RequestBody UserDto admin) throws Exception {

        String response;
        try{
            response = forgetPasswordService.forgetPassword(admin.getEmail());
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PostMapping("/verify-account")
    public ResponseEntity<String> validateOtp(@RequestParam String otp,
                                                @RequestBody LoginDto request) throws Exception {
        String response;

        try{
            response = forgetPasswordService.validateOtp(request.getEmail(),otp);

        }catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }
    @PutMapping("/changePassword")
    public ResponseEntity<String>changePasswordHandle(@RequestBody UserDto admin, ValidateDto validateDto){
        String response;
        try{
            response = forgetPasswordService.changePassword(validateDto.getKey(), admin.getEmail(), admin.getPassword());
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
