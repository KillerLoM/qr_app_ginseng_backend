package qr.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import qr.app.backend.dto.UserDto;
import qr.app.backend.model.User;
import qr.app.backend.repo.UserRepo;
import qr.app.backend.utils.JwtUtils;

@Service
public class SignUpService {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    public String SignUp(UserDto userDto) throws Exception {
        User admin = userRepo.findUserByEmail(userDto.getEmail());
        if(admin != null){
            throw new Exception("Admin has already exit");
        }
        User admin1 = new User();
        admin1.setName(userDto.getName());
        admin1.setEmail(userDto.getEmail());
        admin1.setPassword(passwordEncoder.encode(userDto.getPassword()));
        admin1.setRole("Admin");

        if(userRepo.save(admin1).getId() > 1){
            return "OK";
        }
        else throw new Exception("FAIL");
    }

}
