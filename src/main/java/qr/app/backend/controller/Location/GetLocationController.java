package qr.app.backend.controller.Location;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Location;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.repo.LocationRepo;
import qr.app.backend.response.GinsengResponse;
import qr.app.backend.response.LocationResponse;
import qr.app.backend.utils.JwtUtils;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("admin/location")
public class GetLocationController {
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private JwtUtils jwtUtils;
    @GetMapping("/get")
    public HttpEntity<?> getGinseng(@RequestHeader(value = "Authorization")String token,
                                    @RequestParam(required = false, defaultValue = "10") int size,
                                    @RequestParam(required = false, defaultValue = "0") int page){
        try{
            jwtUtils.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        LocationResponse response = new LocationResponse();
        response.setAmount((int) locationRepo.count());
        PageRequest pageable = PageRequest.of(page, size);
        LinkedList<Location> locationList = new LinkedList<>(locationRepo.findAll(pageable).getContent());
        response.setLocations(locationList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/search")
    public HttpEntity<?> searchLocation(@RequestHeader(value = "Authorization")String token,
                                       @RequestParam(required = false, defaultValue = "Quận ") String address,
                                       @RequestParam(required = false, defaultValue = "Miền ") String description){
        LocationResponse response = new LocationResponse();
        response.setAmount((int) locationRepo.count());
        try{
            jwtUtils.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<Location> searchResult = (List<Location>) locationRepo.findLocationByAddressContainingOrDescriptionContaining(address, description);
        response.setAmount(searchResult.size());
        response.setLocations(searchResult);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
