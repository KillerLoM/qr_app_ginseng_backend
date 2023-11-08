package qr.app.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "ginsengs")
public class Ginseng {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String certificate;
    @Column(nullable = false)
    private Date created_date;
    @Column(nullable = false)
    private String code;
    private String effect;
    @Column(nullable = false)
    private String img1;
    private String img2;
    private String img3;
    private String img4;
    private String img5;
    private String more_info;

}
