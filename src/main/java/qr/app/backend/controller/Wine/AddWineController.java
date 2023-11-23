package qr.app.backend.controller.Wine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qr.app.backend.dto.WineDto;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Wine;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.repo.WineRepo;
import qr.app.backend.service.FileUploadService;
import qr.app.backend.service.ValidGinseng;
import qr.app.backend.utils.JwtUtils;
import qr.app.backend.utils.OTPUtils;

import java.util.List;

@Controller
@RequestMapping("admin/wine/add")
public class AddWineController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private WineRepo wineRepo;
    @Autowired
    private OTPUtils otpUtils;
    @Autowired
    private GinsengRepo ginsengRepo;
    @Autowired
    private FileUploadService fileUploadService;
    @Autowired
    private ValidGinseng validGinseng;
    @PostMapping("")
    public ResponseEntity<String> addWine(@RequestHeader(value = "Authorization", required = true) String token,
                                          @RequestPart Wine wine,
                                          @RequestPart("files") List<MultipartFile> files){
        try {
            jwtUtils.validateToken(token);

            Wine wine1 = wineRepo.findWinesByCodewine(wine.getCodewine());
            if (wine1 != null) {
                return new ResponseEntity<>("The code has been already exit.", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            wine.setOtp(otpUtils.generateOtp());
            boolean check = validGinseng.checkValidGinseng(wine.getGinseng().getCode());
            if (!check) {
                return new ResponseEntity<>("We don't have this ginseng code", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            wine.setGinseng(validGinseng.getGinseng());
            wine.setOtp(otpUtils.generateOtp());
            for (int i = 0; i < Math.min(files.size(), 5); i++) {
                String filePath = fileUploadService.uploadImageWine(files.get(i));
                switch (i) {
                    case 0:
                        wine.setImage(filePath);
                        break;
                    case 1:
                        wine.setImage1(filePath);
                        break;
                    case 2:
                        wine.setImage2(filePath);
                        break;
                    case 3:
                        wine.setImage3(filePath);
                        break;
                    case 4:
                        wine.setImage4(filePath);
                        break;
                }
            }
            wineRepo.save(wine);
        }catch(Exception e ){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        System.out.println(wine.getNamewine());

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
