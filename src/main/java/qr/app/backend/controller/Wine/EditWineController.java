package qr.app.backend.controller.Wine;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import qr.app.backend.dto.GinsengDto;
import qr.app.backend.dto.WineDto;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Wine;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.repo.WineRepo;
import qr.app.backend.utils.JwtUtils;

import java.util.Date;
@Controller
public class EditWineController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private WineRepo wineRepo;
    @Autowired
    private ModelMapper modelMapper;
    @PutMapping("/edit")
    public ResponseEntity<String> updateProduct(@RequestHeader(value = "Authorization") String token, @RequestBody WineDto wineDto) {
        boolean check;
        try {
            jwtUtils.validateToken(token);
            Wine wine = wineRepo.findWinesByCodewine(wineDto.getCodewine());
            if (wine == null) {
                return new ResponseEntity<>("The product is not present", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            modelMapper.map(wineDto, wine, "id");
            wineRepo.save(wine);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
