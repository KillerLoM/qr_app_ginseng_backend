package qr.app.backend.controller.Ginseng;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qr.app.backend.dto.GinsengDto;
import qr.app.backend.model.Ginseng;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.service.FileUploadService;
import qr.app.backend.utils.JwtUtils;

import java.util.List;

@Controller
@RequestMapping("admin/ginseng/edit")
public class EditGinsengController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private GinsengRepo ginsengRepo;
    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private FileUploadService fileUploadService;
    @PutMapping("")
    public ResponseEntity<String> updateProduct(@RequestHeader(value = "Authorization") String token,
                                                @RequestPart("ginseng") GinsengDto ginseng,
                                                @RequestPart(value = "files", required = false) List<MultipartFile> files,
                                                @RequestPart(value = "file", required = false) MultipartFile certi) {

        try {
            jwtUtils.validateToken(token);
            Ginseng ginseng1 = ginsengRepo.findGinsengByCode(ginseng.getCode());
            if (ginseng1 == null) {
                return new ResponseEntity<>("The product is not present", HttpStatus.INTERNAL_SERVER_ERROR);
            }
                ginseng1.setName(ginseng.getName());
                ginseng1.setMore_info(ginseng.getMore_info());
                ginseng1.setSource(ginseng.getSource());
                ginseng1.setCreated_date(ginseng.getCreated_date());
                ginseng1.setImg(ginseng.getImg());
                ginseng1.setImg1(ginseng.getImg1());
                ginseng1.setImg2(ginseng.getImg2());
                ginseng1.setImg3(ginseng.getImg3());
                ginseng1.setImg4(ginseng.getImg4());
                if(files != null){
                    for (int i = 0; i < Math.min(files.size(), 5); i++) {
                        String filePath = fileUploadService.uploadImageGinseng(files.get(i));
                        switch (i) {
                            case 0:
                                if(ginseng1.getImg() == null ){
                                    ginseng1.setImg(filePath);
                                    break;
                                    }
                                if(ginseng1.getImg1() == null ){
                                    ginseng1.setImg1(filePath);
                                    break;
                                }
                                if(ginseng1.getImg2() == null ){
                                    ginseng1.setImg2(filePath);
                                    break;
                                }
                                if(ginseng1.getImg3() == null ){
                                    ginseng1.setImg3(filePath);
                                    break;
                                }
                                else ginseng1.setImg4(filePath);
                                break;
                            case 1:
                                if(ginseng1.getImg1() == null ){
                                    ginseng1.setImg1(filePath);
                                    break;
                                }
                                if(ginseng1.getImg2() == null ){
                                    ginseng1.setImg2(filePath);
                                    break;
                                }
                                if(ginseng1.getImg3() == null ){
                                    ginseng1.setImg3(filePath);
                                    break;
                                }
                                else ginseng1.setImg4(filePath);
                                break;
                            case 2:
                                if(ginseng1.getImg2() == null)
                                    ginseng1.setImg2(filePath);
                                else if(ginseng1.getImg3() == null)
                                    ginseng1.setImg3(filePath);
                                else ginseng1.setImg4(filePath);
                                break;
                            case 3:
                                if(ginseng1.getImg3() == null)
                                    ginseng1.setImg3(filePath);
                                else ginseng1.setImg4(filePath);
                                break;
                            case 4:
                                ginseng1.setImg4(filePath);
                                break;
                        }
                    }
                }
             if(certi != null){
                 String certificate = fileUploadService.uploadCertificate(certi);
                 ginseng1 .setCertificate(certificate);
             }


            ginsengRepo.save(ginseng1);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
