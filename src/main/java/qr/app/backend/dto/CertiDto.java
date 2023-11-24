package qr.app.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CertiDto {
    private int id;
    private String avatar;
    private String namecerti;
    private String description;
    private Date dated;
    private String certificate;
}
