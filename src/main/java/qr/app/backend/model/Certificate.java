package qr.app.backend.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "certificate")
public class Certificate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String avatar;
    private String namecerti;
    private String description;
    private Date dated;
    private String certificate;
}
