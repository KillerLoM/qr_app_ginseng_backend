package qr.app.backend.controller.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import qr.app.backend.model.Location;
import qr.app.backend.repo.LocationRepo;
import qr.app.backend.response.LocationResponse;
import qr.app.backend.utils.JwtUtils;

@Controller
@RequestMapping("admin/location")
public class AddLocationController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private LocationRepo locationRepo;

    @PostMapping("")
    public ResponseEntity<?> addLocation(@RequestHeader(value = "Authorization") String token,
                                         @RequestBody Location location){
        try{
            Location location1 = locationRepo.findLocationByAddress(location.getAddress());
            if(location1 != null){
                return new ResponseEntity<>("The address has already exit!", HttpStatus.INTERNAL_SERVER_ERROR);
            }

        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        locationRepo.save(location);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
