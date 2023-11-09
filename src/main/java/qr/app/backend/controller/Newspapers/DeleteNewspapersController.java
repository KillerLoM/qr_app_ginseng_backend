package qr.app.backend.controller.Newspapers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Newspapers;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.repo.NewspapersRepo;
import qr.app.backend.utils.JwtUtils;

@Controller
@RequestMapping("admin/news/delete")
public class DeleteNewspapersController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private NewspapersRepo newspapersRepo;
    @DeleteMapping("")
    public ResponseEntity<?> deleteNewspapers(@RequestHeader(value = "Authorization")String token,
                                                 @RequestParam int id){
        try{
            jwtUtils.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        Newspapers newspapers = newspapersRepo.findNewspapersById(id);
        if(newspapers == null){
            return new ResponseEntity<>("NOT FOUND", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        newspapersRepo.delete(newspapers);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
