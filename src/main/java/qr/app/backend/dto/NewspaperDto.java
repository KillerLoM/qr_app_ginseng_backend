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
public class NewspaperDto {
    private int id;
    private String name;
    private String avatar;
    private Date date;
    private String description;
    private String link;
    private boolean hide;
}
