package qr.app.backend.model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
@Entity
@Table(name = "wines")
public class Wine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_wine;
    private String codewine;
    private String namewine;
    private String otp;
    private  String image;
    private  String image1;
    private  String image2;
    private  String image3;
    private  String image4;
    private  String volumewine;
    private Date created_date;
    private boolean is_hidden;
    @ManyToOne
    @JoinTable(name = "ginseng_wine",
            joinColumns = @JoinColumn(name = "wine_id"),
            inverseJoinColumns = @JoinColumn(name = "ginseng_id"))
    private Ginseng ginseng;
    @ManyToMany
    @JoinTable(name = "ginseng_wine",
            joinColumns = @JoinColumn(name = "wine_id"),
            inverseJoinColumns = @JoinColumn(name = "ginseng_id"))
    private List<Ginseng> ginsengs;
}
