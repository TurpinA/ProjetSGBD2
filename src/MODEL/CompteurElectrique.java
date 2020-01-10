package MODEL;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;

@Entity
public class CompteurElectrique {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private String numeroCompteur;
    private String adresse;
    private Date dateActivation;

    private Personne personneAssocie;
}
