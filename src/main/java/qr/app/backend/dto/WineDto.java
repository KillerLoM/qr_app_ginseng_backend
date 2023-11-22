package qr.app.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import qr.app.backend.model.Ginseng;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class WineDto {
    private String codewine;
    private String namewine;
    private String otp;
    private  String imagewine1;
    private  String volumewine;
    private Date created_date;
    private  String imagewine2;
    private  String imagewine3;
    private  String imagewine4;
    private  String imagewine5;
    private String qr;
    private boolean is_hidden;
    private Ginseng ginseng;
    private List<Ginseng> ginsengs;
}
