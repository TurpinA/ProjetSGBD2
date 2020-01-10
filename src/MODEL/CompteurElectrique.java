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

    public CompteurElectrique(String numeroCompteur, String adresse, Date dateActivation, Personne personneAssocie, List<PlageHorraire> consomation) {
        this.numeroCompteur = numeroCompteur;
        this.adresse = adresse;
        this.dateActivation = dateActivation;
        this.personneAssocie = personneAssocie;
        this.consomation = consomation;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNumeroCompteur() {
        return numeroCompteur;
    }

    public void setNumeroCompteur(String numeroCompteur) {
        this.numeroCompteur = numeroCompteur;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Date getDateActivation() {
        return dateActivation;
    }

    public void setDateActivation(Date dateActivation) {
        this.dateActivation = dateActivation;
    }

    public Personne getPersonneAssocie() {
        return personneAssocie;
    }

    public void setPersonneAssocie(Personne personneAssocie) {
        this.personneAssocie = personneAssocie;
    }

    public List<PlageHorraire> getConsomation() {
        return consomation;
    }

    public void setConsomation(List<PlageHorraire> consomation) {
        this.consomation = consomation;
    }
}
