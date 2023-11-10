package qr.app.backend.controller.Location;

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
import qr.app.backend.dto.LocationDto;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Location;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.repo.LocationRepo;
import qr.app.backend.utils.JwtUtils;
@Controller
@RequestMapping("admin/location")
public class EditLocationController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private LocationRepo locationRepo;
    @Autowired
    private ModelMapper modelMapper;
    @PutMapping("/edit")
    public ResponseEntity<String> updateProduct(@RequestHeader(value = "Authorization") String token, @RequestBody LocationDto locationDto) {
        try {
            jwtUtils.validateToken(token);
            Location location = locationRepo.findLocationByAddress(locationDto.getAddress());
            if (location == null) {
                return new ResponseEntity<>("The location is not present", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            modelMapper.map(locationDto, location, "id");
            locationRepo.save(location);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
