package qr.app.backend.controller.Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import qr.app.backend.dto.CertiDto;
import qr.app.backend.model.Certificate;
import qr.app.backend.repo.CertiRepo;
import qr.app.backend.service.FileUploadService;
import qr.app.backend.utils.JwtUtils;

import java.io.File;

@Controller
@RequestMapping("admin/certificate/add")
public class AddCertiController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CertiRepo certiRepo;
    @Autowired
    private FileUploadService fileUploadService;

    @PostMapping("")
    public ResponseEntity<?> addGinseng(@RequestHeader(value = "Authorization") String token,
                                        @RequestPart("certificate") CertiDto certiDto,
                                        @RequestPart("fileCerti") MultipartFile file,
                                        @RequestPart("image") MultipartFile image){
        try{
            jwtUtils.validateToken(token);
            Certificate certi = new Certificate();
            String filePath = fileUploadService.uploadCertificate(file);
            String imagePath = fileUploadService.uploadAvatar(image);
            System.out.println(imagePath);
            certi.setAvatar(imagePath);
            certi.setCertificate(filePath);
            certi.setDescription(certiDto.getDescription());
            certi.setNamecerti(certiDto.getNamecerti());
            certi.setDated(certiDto.getDated());
            certiRepo.save(certi);
            return new ResponseEntity<>("OK", HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
}
