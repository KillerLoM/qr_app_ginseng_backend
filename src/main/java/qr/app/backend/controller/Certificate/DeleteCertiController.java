package qr.app.backend.controller.Certificate;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qr.app.backend.model.Certificate;
import qr.app.backend.repo.CertiRepo;
import qr.app.backend.utils.JwtUtils;

@Controller
@RequestMapping("admin/certificate/delete")
public class DeleteCertiController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CertiRepo certiRepo;
    @DeleteMapping("")
    public ResponseEntity<?> deleteCerti(@RequestHeader(value = "Authorization")String token,
                                         @RequestParam Integer id){

    try{
        jwtUtils.validateToken(token);
        Certificate certificate = certiRepo.findCertificateById(id);
        certiRepo.delete(certificate);
    }catch (Exception e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
    }
    return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
