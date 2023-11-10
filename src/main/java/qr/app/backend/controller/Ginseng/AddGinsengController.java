package qr.app.backend.controller.Ginseng;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qr.app.backend.model.Ginseng;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.service.FileUploadService;
import qr.app.backend.utils.JwtUtils;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("admin/ginseng")
public class AddGinsengController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private GinsengRepo ginsengRepo;

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("/add")
    public ResponseEntity<String> addGinseng(@RequestHeader(value = "Authorization") String token,
                                             @RequestPart("ginseng") Ginseng ginseng,
                                             @RequestPart("files") List<MultipartFile> files,
                                             @RequestPart("file") MultipartFile certi) {
        StringBuilder img = new StringBuilder();
        String certificate ;
        try {
            jwtUtils.validateToken(token);
            Ginseng ginseng1 = ginsengRepo.findGinsengByCode(ginseng.getCode());
            if (ginseng1 != null) {
                return new ResponseEntity<>("The code has been already exist.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            for (MultipartFile file : files) {
                String filePath = "";
                filePath = fileUploadService.uploadImageGinseng(file);
                img.append(filePath).append(";");

            }
            certificate = fileUploadService.uploadCertificate(certi);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        ginseng.setImg(img.toString());
        ginseng.setCertificate(certificate);
        ginsengRepo.save(ginseng);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
