package qr.app.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
}
