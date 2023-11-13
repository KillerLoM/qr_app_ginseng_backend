package qr.app.backend.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.Formula;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "ginsengs")
public class Ginseng {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(updatable = false)
    private int id;
    private String name;
    private String certificate;
    private Date created_date;
    private String code;
    private String effect;
    private String img;
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String source;
    private String more_info;
    @Formula("(SELECT COUNT(*) FROM ginseng_wine gw WHERE gw.ginseng_id = id)")
    private int appearanceCount;
    @JsonIgnore
    @OneToMany(mappedBy = "ginseng")
    private List<Wine> wines;

}
