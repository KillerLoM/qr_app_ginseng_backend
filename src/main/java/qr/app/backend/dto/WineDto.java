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
    private  String volumewine;
    private int cc;
    private Date created_date;
    private String effect;
    private  String moreinfo;
    private  String image;
    private  String image1;
    private  String image2;
    private  String image3;
    private  String image4;
    private String qr;
    private boolean is_hidden;
    private String otp;
    private Ginseng ginseng;
    private List<Ginseng> ginsengs;
}
