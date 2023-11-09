package qr.app.backend.controller.Wine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import qr.app.backend.dto.WineDto;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Wine;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.repo.WineRepo;
import qr.app.backend.utils.JwtUtils;

@Controller
public class DeleteWineController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private WineRepo wineRepo;
    @DeleteMapping("")
    public ResponseEntity<?> deleteGinsengByCode(@RequestHeader(value = "Authorization")String token,
                                                 @RequestParam String code){
        try{
            jwtUtils.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        Wine wine = wineRepo.findWinesByCodewine(code);
        if(wine == null){
            return new ResponseEntity<>("NOT FOUND", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        wineRepo.delete(wine);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
