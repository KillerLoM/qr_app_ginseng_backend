package qr.app.backend.controller.Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import qr.app.backend.dto.CertiDto;
import qr.app.backend.dto.GinsengDto;
import qr.app.backend.model.Certificate;
import qr.app.backend.repo.CertiRepo;
import qr.app.backend.service.FileUploadService;
import qr.app.backend.utils.JwtUtils;

import java.util.List;

@Controller
@RequestMapping("admin/certificate/edit")
public class EditCertiController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private CertiRepo certiRepo;
    @Autowired
    private FileUploadService fileUploadService;
    @PutMapping("")
    public ResponseEntity<?> updateCerti(@RequestHeader(value = "Authorization") String token,
                                         @RequestPart("certificate") CertiDto certiDto,
                                         @RequestPart(value = "fileCerti", required = false) MultipartFile file,
                                         @RequestPart(value = "image", required = false) MultipartFile image){
        try{
            jwtUtils.validateToken(token);
            Certificate certificate = certiRepo.findCertificateById(certiDto.getId());
            if(certificate == null){
                return new ResponseEntity<>("The certificate is not present", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            certificate.setCertificate(certiDto.getCertificate());
            certificate.setAvatar(certiDto.getAvatar());
            certificate.setDated(certiDto.getDated());
            certificate.setNamecerti(certiDto.getNamecerti());
            certificate.setDescription(certiDto.getDescription());
            if(file != null){
                String filePath = fileUploadService.uploadCertificate(file);
                certificate.setCertificate(filePath);
                System.out.println(filePath);
            }
            if(image != null){
                String filePath = fileUploadService.uploadAvatar(image);
                System.out.println(filePath);
                certificate.setAvatar(filePath);
            }
            certiRepo.save(certificate);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}

