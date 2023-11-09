package qr.app.backend.dto;

import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import qr.app.backend.model.Wine;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GinsengDto {
    private String name;
    private String certificate;
    private Date created_date;
    private String code;
    private String effect;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String more_info;
//    @OneToMany(mappedBy = "ginseng")
//    private List<Wine> wines;
}
