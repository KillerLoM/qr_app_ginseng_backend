package qr.app.backend.controller.Newspapers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Newspapers;
import qr.app.backend.repo.NewspapersRepo;
import qr.app.backend.service.FileUploadService;
import qr.app.backend.utils.JwtUtils;

@Controller
@RequestMapping("admin/news")
public class AddNewspapersController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private NewspapersRepo newspapersRepo;
    @Autowired
    private FileUploadService fileUploadService;
    @PostMapping("/add")
    public ResponseEntity<String> addNewspapers(@RequestHeader(value = "Authorization") String token,
                                                @RequestPart Newspapers newspapers,
                                                @RequestPart MultipartFile file) throws Exception {
        try {
            jwtUtils.validateToken(token);
            Newspapers newspapers1 = newspapersRepo.findNewspapersByLink(newspapers.getLink());
            if (newspapers1 != null) {
                return new ResponseEntity<>("The newspapers has been already exit.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            String filePath = fileUploadService.uploadAvatar(file);
            newspapers.setAvatar(filePath);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        newspapersRepo.save(newspapers);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
