package qr.app.backend.controller.Certificate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import qr.app.backend.dto.CertiDto;
import qr.app.backend.model.Certificate;
import qr.app.backend.model.Ginseng;
import qr.app.backend.repo.CertiRepo;
import qr.app.backend.response.CertiResponse;
import qr.app.backend.response.GinsengResponse;
import qr.app.backend.utils.JwtUtils;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("admin/certificate/get")
public class GetCertiController {
    @Autowired
    private CertiRepo certiRepo;
    @Autowired
    private JwtUtils jwtUtils;
    @GetMapping("")
    public HttpEntity<?> getCertiList(@RequestHeader(value = "Authorization")String token,
                                    @RequestParam(required = false, defaultValue = "10") int size,
                                    @RequestParam(required = false, defaultValue = "0") int page){
        CertiResponse response = new CertiResponse();
        try{
            jwtUtils.validateToken(token);
            response.setAmount((int) certiRepo.count());
            PageRequest pageable = PageRequest.of(page, size);
            LinkedList<Certificate> certificates = new LinkedList<>(certiRepo.findAll(pageable).getContent());
            response.setCertificates(certificates);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/detail")
    public HttpEntity<?> getCerti(@RequestHeader(value = "Authorization")String token,
                                    @RequestParam()Integer id){
        try{
            jwtUtils.validateToken(token);
            Certificate certificate =  certiRepo.findCertificateById(id);
            return new ResponseEntity<>(certificate, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
    }
    @GetMapping("/search")
    public HttpEntity<?> searchCerti(@RequestHeader(value = "Authorization")String token,
                                       @RequestParam(required = false, defaultValue = "NS") String name,
                                       @RequestParam(required = false, defaultValue = "N") String description){
        CertiResponse response = new CertiResponse();
        response.setAmount((int) certiRepo.count());
        try{
            jwtUtils.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<Certificate> searchResult = certiRepo.findByNamecertiContainingOrDescriptionContaining(name, description);
        response.setAmount(searchResult.size());
        response.setCertificates(searchResult);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
