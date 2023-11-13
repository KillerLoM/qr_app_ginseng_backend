package qr.app.backend.controller.Ginseng;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import qr.app.backend.dto.GinsengDto;
import qr.app.backend.model.Ginseng;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.utils.JwtUtils;

@Controller
@RequestMapping("admin/ginseng/edit")
public class EditGinsengController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private GinsengRepo ginsengRepo;
    @Autowired
    private ModelMapper modelMapper;
    @PutMapping("")
    public ResponseEntity<String> updateProduct(@RequestHeader(value = "Authorization") String token, @RequestBody GinsengDto ginseng) {
        boolean check;
        try {
            jwtUtils.validateToken(token);
            Ginseng ginseng1 = ginsengRepo.findGinsengByCode(ginseng.getCode());
            if (ginseng1 == null) {
                return new ResponseEntity<>("The product is not present", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            modelMapper.map(ginseng, ginseng1, "id");
            ginsengRepo.save(ginseng1);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
