package qr.app.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Entity
@Data
@Table(name = "newspapers")
public class Newspapers {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String avatar;
    private Date date;
    private String description;
    private String link;
    private boolean hide;

}
