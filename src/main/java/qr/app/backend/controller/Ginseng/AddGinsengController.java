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
@RequestMapping("admin/ginseng/add")
public class AddGinsengController {

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private GinsengRepo ginsengRepo;

    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("")
    public ResponseEntity<String> addGinseng(@RequestHeader(value = "Authorization") String token,
                                             @RequestPart("ginseng") Ginseng ginseng,
                                             @RequestPart("files") List<MultipartFile> files,
                                             @RequestPart("file") MultipartFile certi) {
        try {
            jwtUtils.validateToken(token);
            Ginseng ginseng1 = ginsengRepo.findGinsengByCode(ginseng.getCode());
            if (ginseng1 != null) {
                return new ResponseEntity<>("The code has been already exist.", HttpStatus.INTERNAL_SERVER_ERROR);
            }

            // Upload images
            for (int i = 0; i < Math.min(files.size(), 5); i++) {
                String filePath = fileUploadService.uploadImageGinseng(files.get(i));
                switch (i) {
                    case 0:
                        ginseng.setImg(filePath);
                        break;
                    case 1:
                        ginseng.setImg1(filePath);
                        break;
                    case 2:
                        ginseng.setImg2(filePath);
                        break;
                    case 3:
                        ginseng.setImg3(filePath);
                        break;
                    case 4:
                        ginseng.setImg4(filePath);
                        break;
                }
            }
            String certificate = fileUploadService.uploadCertificate(certi);
            ginseng.setCertificate(certificate);
            ginsengRepo.save(ginseng);
            return new ResponseEntity<>("OK", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }

}
