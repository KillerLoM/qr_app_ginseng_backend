package qr.app.backend.model;

import jakarta.persistence.*;
import lombok.Data;

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
    private String more_info;
    @OneToMany(mappedBy = "ginseng")
    private List<Wine> wines;
}
