package qr.app.backend.controller.Newspapers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Newspapers;
import qr.app.backend.repo.NewspapersRepo;
import qr.app.backend.utils.JwtUtils;

@Controller
@RequestMapping("admin/news")
public class AddNewspapersController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private NewspapersRepo newspapersRepo;
    @PostMapping("/add")
    public ResponseEntity<String> addNewspapers(@RequestHeader(value = "Authorization") String token, @RequestBody Newspapers newspapers) throws Exception {
        boolean check;
        try {
            jwtUtils.validateToken(token);

            Newspapers newspapers1 = newspapersRepo.findNewspapersByLink(newspapers.getLink());
            if (newspapers1 != null) {
                return new ResponseEntity<>("The newspapers has been already exit.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            try {
                newspapersRepo.save(newspapers);
            } catch (Exception e) {
                return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
