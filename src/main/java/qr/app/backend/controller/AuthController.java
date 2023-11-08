package qr.app.backend.controller;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qr.app.backend.config.PasswordEncode;
import qr.app.backend.model.User;
import qr.app.backend.repo.UserRepo;
import qr.app.backend.request.LoginRequest;
import qr.app.backend.request.Validate;
import qr.app.backend.response.LoginResponse;
import qr.app.backend.utils.JwtUtils;

@Controller
@RequestMapping("/admin")
public class AuthController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private PasswordEncode passwordEncode;
    @CrossOrigin(origins = "*")
    @PostMapping("/authenticate")
    public ResponseEntity<LoginResponse> loginHandle(@RequestBody LoginRequest request){
        String password = request.getPassword();
        String email = request.getEmail();
        User admin = userRepo.findUserByEmail(email);
        if(admin == null){
            return new ResponseEntity<>(LoginResponse.fail("Admin not found"), HttpStatus.UNAUTHORIZED);
        }
        if(new BCryptPasswordEncoder().matches(password, admin.getPassword())){

            LoginResponse response = new LoginResponse(email, true, jwtUtils.generateToken(email),"ok");
            return new ResponseEntity<>(response, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(LoginResponse.fail("PASSWORD IS NOT CORRECT"), HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/authenticate/validate-token")
    public ResponseEntity<?> validateToken(@RequestBody Validate request){
        String response = "";
        try{
            response = jwtUtils.validateToken(request.getToken());
        } catch (ExpiredJwtException e){
            return new ResponseEntity<>("token is expired. Description: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        } catch(JwtException | IllegalArgumentException e){
            return new ResponseEntity<>("invalid token. Description: " + e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
