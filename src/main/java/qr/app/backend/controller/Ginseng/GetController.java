package qr.app.backend.controller.Ginseng;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qr.app.backend.model.Ginseng;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.response.GinsengResponse;
import qr.app.backend.utils.JwtUtils;


@Controller
@RequestMapping("admin/ginseng")
public class GetController {
    @Autowired
    private GinsengRepo ginsengRepo;
    @Autowired
    private JwtUtils jwtUtils;
    @GetMapping("/get")
    public HttpEntity<?> getGinseng(@RequestHeader(value = "Authorization")String token,
                                 @RequestParam(required = false, defaultValue = "10") int size,
                                 @RequestParam(required = false, defaultValue = "0") int page){
        try{
            jwtUtils.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        GinsengResponse response = new GinsengResponse();
        response.setAmount((int) ginsengRepo.count());
        PageRequest pageable = PageRequest.of(page, size);
        LinkedList<Ginseng> ginsengList = new LinkedList<>(ginsengRepo.findAll(pageable).getContent());
        response.setGinsengs(ginsengList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/search")
    public HttpEntity<?> searchGinseng(@RequestHeader(value = "Authorization")String token,
                                    @RequestParam(required = false, defaultValue = "NS") String code,
                                    @RequestParam(required = false, defaultValue = "N") String name){
        GinsengResponse response = new GinsengResponse();
        response.setAmount((int) ginsengRepo.count());
        try{
            jwtUtils.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<Ginseng> searchResult = ginsengRepo.findByCodeContainingOrNameContaining(code, name);
        response.setAmount(searchResult.size());
        response.setGinsengs(searchResult);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
