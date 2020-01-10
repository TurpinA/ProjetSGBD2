package MODEL;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;

@Entity
public class PlageHorraire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private Date date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Double puissanceConsomme;

    @ManyToOne
    private CompteurElectrique compteur;
}
