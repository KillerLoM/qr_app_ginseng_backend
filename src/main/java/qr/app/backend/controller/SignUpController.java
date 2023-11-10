package qr.app.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import qr.app.backend.dto.UserDto;
import qr.app.backend.repo.UserRepo;
import qr.app.backend.service.SignUpService;
import qr.app.backend.utils.JwtUtils;

import java.util.Objects;
@Controller
public class SignUpController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private SignUpService signUp;

    @PostMapping("/register")
    public ResponseEntity<String> signUpHandle(@RequestHeader(value = "Authorization", required = true) String tokenIn,
                                               @RequestBody UserDto userDto) {
        String response;
        String output = null;
        try {
            jwtUtils.validateToken(tokenIn);
                try {
                    response = jwtUtils.getRoleFromToken(tokenIn);
                    if (Objects.equals(response, "Super_admin")) {
                        try {
                            output = signUp.SignUp(userDto);
                        } catch (Exception e) {
                            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
                        }
                    } else {
                        return new ResponseEntity<>("You can not create new Admin", HttpStatus.UNAUTHORIZED);
                    }
                } catch (Exception e) {
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
                }
            return new ResponseEntity<>(output, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
