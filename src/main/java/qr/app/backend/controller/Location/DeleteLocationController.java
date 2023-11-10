package qr.app.backend.controller.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Location;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.repo.LocationRepo;
import qr.app.backend.utils.JwtUtils;
@Controller
@RequestMapping("admin/location/delete")
public class DeleteLocationController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private LocationRepo locationRepo;
    @DeleteMapping("")
    public ResponseEntity<?> deleteGinsengByCode(@RequestHeader(value = "Authorization")String token,
                                                 @RequestParam String address){
        try{
            jwtUtils.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        Location location = locationRepo.findLocationByAddress(address);
        if(location == null){
            return new ResponseEntity<>("NOT FOUND", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        locationRepo.delete(location);
        return new ResponseEntity<>("DELETED", HttpStatus.OK);
    }
}
