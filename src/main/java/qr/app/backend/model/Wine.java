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
    @Column(nullable = false)
    private String codewine;
    @Column(nullable = false)
    private String namewine;
    @Column(nullable = false)
    private String otp;
    @Column(nullable = false)
    private  String imagewine1;
    @Column(nullable = false)
    private  String volumewine;
    @Column(nullable = false)
    private Date created_date;
    private  String imagewine2;
    private  String imagewine3;
    private  String imagewine4;
    private  String imagewine5;
    private String qr;
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
