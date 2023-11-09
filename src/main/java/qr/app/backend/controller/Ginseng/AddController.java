package qr.app.backend.controller.Ginseng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import qr.app.backend.model.Ginseng;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.utils.JwtUtils;

@Controller
@RequestMapping("admin/ginseng")
public class AddController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private GinsengRepo ginsengRepo;

    @PostMapping("/add")
    public ResponseEntity<String> addGinseng(@RequestHeader(value = "Authorization") String token, @RequestBody Ginseng ginseng) throws Exception {
        boolean check;
        try {
            jwtUtils.validateToken(token);

            Ginseng ginseng1 = ginsengRepo.findGinsengByCode(ginseng.getCode());
            if (ginseng1 != null) {
                return new ResponseEntity<>("The code has been already exit.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            try {
                ginsengRepo.save(ginseng);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
