package qr.app.backend.controller.Newspapers;

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
import qr.app.backend.model.Ginseng;
import qr.app.backend.model.Newspapers;
import qr.app.backend.repo.GinsengRepo;
import qr.app.backend.repo.NewspapersRepo;
import qr.app.backend.response.GinsengResponse;
import qr.app.backend.response.NewspapersResponse;
import qr.app.backend.utils.JwtUtils;

import java.util.LinkedList;
import java.util.List;

@Controller
@RequestMapping("admin/news")
public class GetNewspapers {
    @Autowired
    private NewspapersRepo newsRepo;
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
        NewspapersResponse response = new NewspapersResponse();
        response.setAmount((int) newsRepo.count());
        PageRequest pageable = PageRequest.of(page, size);

        LinkedList<Newspapers> newspapersList = new LinkedList<>(newsRepo.findAll(pageable).getContent());
        response.setNewspapers(newspapersList);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
    @GetMapping("/search")
    public HttpEntity<?> searchGinseng(@RequestHeader(value = "Authorization")String token,
                                       @RequestParam(required = false, defaultValue = "Kinh") String description,
                                       @RequestParam(required = false, defaultValue = "B") String name){
        NewspapersResponse response = new NewspapersResponse();
        response.setAmount((int) newsRepo.count());
        try{
            jwtUtils.validateToken(token);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.UNAUTHORIZED);
        }
        List<Newspapers> searchResult = newsRepo.findNewspapersByDescriptionContainingOrNameContaining(description, name);
        response.setAmount(searchResult.size());
        response.setNewspapers(searchResult);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
