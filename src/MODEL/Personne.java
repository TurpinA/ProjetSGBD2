package MODEL;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private String numeroSecu;
    private String adresse;
    private String numeroTel;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personneAssocie")
    private List<CompteurElectrique> compteursListe;

    public Personne(int ID, String numeroSecu, String adresse, String numeroTel, ArrayList<CompteurElectrique> compteursListe) {
        this.ID = ID;
        this.numeroSecu = numeroSecu;
        this.adresse = adresse;
        this.numeroTel = numeroTel;
        this.compteursListe = compteursListe;
    }

    public Personne(String numeroSecu, String adresse, String numeroTel, ArrayList<CompteurElectrique> compteursListe) {
        this.numeroSecu = numeroSecu;
        this.adresse = adresse;
        this.numeroTel = numeroTel;
        this.compteursListe = compteursListe;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNumeroSecu() {
        return numeroSecu;
    }

    public void setNumeroSecu(String numeroSecu) {
        this.numeroSecu = numeroSecu;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroTel() {
        return numeroTel;
    }

    public void setNumeroTel(String numeroTel) {
        this.numeroTel = numeroTel;
    }

    public List<CompteurElectrique> getCompteursListe() {
        return compteursListe;
    }

    public void setCompteursListe(ArrayList<CompteurElectrique> compteursListe) {
        this.compteursListe = compteursListe;
    }
}
