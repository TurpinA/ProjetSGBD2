package MODEL;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class CompteurElectrique {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private String numeroCompteur;
    private String adresse;
    private Date dateActivation;

    @ManyToOne
    private Personne personneAssocie;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "compteur")
    private List<PlageHorraire> consomation;
}
