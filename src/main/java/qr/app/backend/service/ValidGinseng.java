package qr.app.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import qr.app.backend.model.Ginseng;
import qr.app.backend.repo.GinsengRepo;

@Service
public class ValidGinseng {
    private Ginseng ginseng;
    @Autowired
    private GinsengRepo ginsengRepo;
    public boolean checkValidGinseng(String code){
         ginseng = ginsengRepo.findGinsengByCode(code);
         setGinseng(ginseng);
        if(ginseng == null){
            return false;
        }
        return true;
    }

    public Ginseng getGinseng() {
        return ginseng;
    }

    public void setGinseng(Ginseng ginseng) {
        this.ginseng = ginseng;
    }
}
