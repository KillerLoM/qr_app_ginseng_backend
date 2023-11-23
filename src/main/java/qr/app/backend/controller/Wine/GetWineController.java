package qr.app.backend.controller.Wine;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Wine;
import qr.app.backend.repo.WineRepo;
import qr.app.backend.response.GinsengResponse;
import qr.app.backend.response.WineResponse;
import qr.app.backend.utils.JwtUtils;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("admin/wine")
public class GetWineController {
    @Autowired
    private JwtUtils jwtUtils;
    @Autowired
    private WineRepo wineRepo;
    @GetMapping("/get")
    public HttpEntity<?> getGinseng(@RequestHeader(value = "Authorization")String token,
                                    @RequestParam(required = false, defaultValue = "10") int size,
                                    @RequestParam(required = false, defaultValue = "0") int page){
        try{
            jwtUtils.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        WineResponse response = new WineResponse();
        response.setAmount((int) wineRepo.count());
        PageRequest pageable = PageRequest.of(page, size);
        LinkedList<Wine> wineList = new LinkedList<>(wineRepo.findAll(pageable).getContent());
        response.setWines(wineList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/getProduct")
    public HttpEntity<?> getWine(@RequestParam()String code){
        try{
            Wine wine = wineRepo.findWinesByCodewine(code);
            return new ResponseEntity<>(wine, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/search")
    public HttpEntity<?> searchWine(@RequestHeader(value = "Authorization")String token,
                                       @RequestParam(required = false, defaultValue = "NS") String code,
                                       @RequestParam(required = false, defaultValue = "N") String name){
        WineResponse response = new WineResponse();
        response.setAmount((int) wineRepo.count());
        try{
            jwtUtils.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<Wine> searchResult = wineRepo.findByCodewineContainingOrNamewineContaining(code, name);
        response.setAmount(searchResult.size());
        response.setWines(searchResult);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
