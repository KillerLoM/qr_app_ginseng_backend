package qr.app.backend.controller.Newspapers;

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
import qr.app.backend.dto.NewspaperDto;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Newspapers;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.repo.NewspapersRepo;
import qr.app.backend.utils.JwtUtils;
@Controller
@RequestMapping("admin/news")
public class EditNewspapers {

    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private NewspapersRepo newsRepo;
    @Autowired
    private ModelMapper modelMapper;
    @PutMapping("/edit")
    public ResponseEntity<String> updateNewspapers(@RequestHeader(value = "Authorization") String token, @RequestBody NewspaperDto newspaperDto) {
        try {
            jwtUtils.validateToken(token);
            Newspapers newspapers = newsRepo.findNewspapersById(newspaperDto.getId());
            if (newspapers == null) {
                return new ResponseEntity<>("The newspapers is not present", HttpStatus.INTERNAL_SERVER_ERROR);
            }
            modelMapper.map(newspaperDto, newspapers, "id");
            newsRepo.save(newspapers);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
