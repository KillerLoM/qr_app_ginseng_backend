package qr.app.backend.controller.Ginseng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import qr.app.backend.model.Ginseng;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.utils.JwtUtils;
@Controller
@RequestMapping("admin/ginseng/delete")
public class DeleteGinsengController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private GinsengRepo ginsengRepo;
    @DeleteMapping("")
    public ResponseEntity<?> deleteGinsengByCode(@RequestHeader(value = "Authorization")String token,
                                                 @RequestParam Long id){
        try{
            jwtUtils.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        Ginseng ginseng = ginsengRepo.findGinsengById(id);
        if(ginseng == null){
            return new ResponseEntity<>("NOT FOUND", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        ginsengRepo.delete(ginseng);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
