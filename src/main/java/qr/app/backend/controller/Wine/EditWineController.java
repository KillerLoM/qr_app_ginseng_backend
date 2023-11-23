package qr.app.backend.controller.Wine;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import qr.app.backend.dto.GinsengDto;
import qr.app.backend.dto.WineDto;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Wine;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.repo.WineRepo;
import qr.app.backend.service.FileUploadService;
import qr.app.backend.service.ValidGinseng;
import qr.app.backend.utils.JwtUtils;

import java.util.List;
import java.util.Date;
@Controller
@RequestMapping("admin/wine")
public class EditWineController {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private WineRepo wineRepo;

    @Autowired
    private ValidGinseng validGinseng;
    @Autowired
    private FileUploadService fileUploadService;
    @PutMapping("/edit")
    public ResponseEntity<String> updateProduct(@RequestHeader(value = "Authorization") String token,
                                                @RequestPart("wine") WineDto wineDto,
                                                @RequestPart(value = "files", required = false)List<MultipartFile> files) {
        boolean check;
        try {
            jwtUtils.validateToken(token);
            Wine wine = wineRepo.findWinesByCodewine(wineDto.getCodewine());
            if (wine == null) {
                return new ResponseEntity<>("The product is not present", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            boolean valid = validGinseng.checkValidGinseng(wineDto.getGinseng().getCode());
            if(!valid){
                return new ResponseEntity<>("The ginseng is not present", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            wine.setGinseng(validGinseng.getGinseng());
            wine.setNamewine(wineDto.getNamewine());
            wine.setMoreinfo(wineDto.getMoreinfo());
            System.out.println(wineDto.getMoreinfo());
            wine.setCreated_date(wineDto.getCreated_date());
            wine.setVolumewine(wineDto.getVolumewine());
            wine.setCc(wineDto.getCc());
            wine.setEffect(wineDto.getEffect());
            wine.setImage(wineDto.getImage());
            wine.setImage1(wineDto.getImage1());
            wine.setImage2(wineDto.getImage2());
            wine.setImage3(wineDto.getImage3());
            wine.setImage4(wineDto.getImage4());

            if(files != null){
                for (int i = 0; i < Math.min(files.size(), 5); i++) {
                    String filePath = fileUploadService.uploadImageGinseng(files.get(i));
                    switch (i) {
                        case 0:
                            if(wine.getImage() == null ){
                                wine.setImage(filePath);
                                break;
                            }
                            if(wine.getImage1() == null ){
                                wine.setImage1(filePath);
                                break;
                            }
                            if(wine.getImage2() == null ){
                                wine.setImage2(filePath);
                                break;
                            }
                            if(wine.getImage3() == null ){
                                wine.setImage3(filePath);
                                break;
                            }
                            else wine.setImage4(filePath);
                            break;
                        case 1:
                            if(wine.getImage1() == null ){
                                wine.setImage1(filePath);
                                break;
                            }
                            if(wine.getImage2() == null ){
                                wine.setImage2(filePath);
                                break;
                            }
                            if(wine.getImage3() == null ){
                                wine.setImage3(filePath);
                                break;
                            }
                            else wine.setImage4(filePath);
                            break;
                        case 2:
                            if(wine.getImage2() == null ){
                                wine.setImage2(filePath);
                                break;
                            }
                            if(wine.getImage3() == null ){
                                wine.setImage3(filePath);
                                break;
                            }
                            else wine.setImage4(filePath);
                            break;
                        case 3:
                            if(wine.getImage3() == null ){
                                wine.setImage3(filePath);
                                break;
                            }
                            else wine.setImage4(filePath);
                            break;
                        case 4:
                            wine.setImage4(filePath);
                            break;
                    }
                }
            }
            wineRepo.save(wine);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
