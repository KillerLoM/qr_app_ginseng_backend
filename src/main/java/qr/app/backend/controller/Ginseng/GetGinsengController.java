package qr.app.backend.controller.Ginseng;

import java.util.LinkedList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
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
@RequestMapping("admin/ginseng/get")
public class GetGinsengController {
    @Autowired
    private GinsengRepo ginsengRepo;
    @Autowired
    private JwtUtils jwtUtils;
    @GetMapping("")
    public HttpEntity<?> getGinseng(@RequestHeader(value = "Authorization")String token,
                                 @RequestParam(required = false, defaultValue = "10") int size,
                                 @RequestParam(required = false, defaultValue = "0") int page){
        GinsengResponse response = new GinsengResponse();
        try{
            jwtUtils.validateToken(token);
            response.setAmount((int) ginsengRepo.count());
            PageRequest pageable = PageRequest.of(page, size);
            LinkedList<Ginseng> ginsengList = new LinkedList<>(ginsengRepo.findAll(pageable).getContent());
            response.setGinsengs(ginsengList);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/code")
    public HttpEntity<?> getGinseng(@RequestHeader(value = "Authorization")String token){
        System.out.println(1);
        try{
            jwtUtils.validateToken(token);
            Pageable pageable = Pageable.unpaged();
            Page<Ginseng> page = ginsengRepo.findAll(pageable);
            List<Ginseng> elements = page.getContent();
            return new ResponseEntity<>(elements, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/detail")
    public HttpEntity<?> getGinseng(@RequestHeader(value = "Authorization")String token,
                                    @RequestParam()Long id){
        try{
            jwtUtils.validateToken(token);
            Ginseng ginseng = ginsengRepo.findGinsengById(id);
            return new ResponseEntity<>(ginseng, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
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
