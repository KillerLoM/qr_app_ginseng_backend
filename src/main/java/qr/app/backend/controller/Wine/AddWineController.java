package qr.app.backend.controller.Wine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Wine;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.repo.WineRepo;
import qr.app.backend.utils.JwtUtils;
import qr.app.backend.utils.OTPUtils;

@Controller
@RequestMapping("admin/wine")
public class AddWineController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private WineRepo wineRepo;
    @Autowired
    private OTPUtils otpUtils;
    @Autowired
    private GinsengRepo ginsengRepo;
    @PostMapping("/add")
    public ResponseEntity<String> addWine(@RequestHeader(value = "Authorization", required = true) String token,
                                          @RequestBody Wine wine){
        try{
            jwtUtils.validateToken(token);
        }catch(Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        Wine wine1 = wineRepo.findWinesByCodewine(wine.getCodewine());
        if(wine1 != null){
            return new ResponseEntity<>("The code has been already exit.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        wine.setOtp(otpUtils.generateOtp());
        Ginseng ginseng = ginsengRepo.findGinsengByCode(wine.getGinseng().getCode());
        if(ginseng == null){
            return new ResponseEntity<>("We don't have this code", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        wine.setGinseng(ginseng);
        wine.setOtp(otpUtils.generateOtp());
        wineRepo.save(wine);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
