package MODEL;

import javax.persistence.*;
import java.util.ArrayList;

@Entity
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private String numeroSecu;
    private String adresse;
    private String numeroTel;

    private ArrayList<PlageHorraire> consomation;
    private ArrayList<CompteurElectrique> compteursListe;

    public Personne(int ID, String numeroSecu, String adresse, String numeroTel, ArrayList<PlageHorraire> consomation, ArrayList<CompteurElectrique> compteursListe) {
        this.ID = ID;
        this.numeroSecu = numeroSecu;
        this.adresse = adresse;
        this.numeroTel = numeroTel;
        this.consomation = consomation;
        this.compteursListe = compteursListe;
    }

    public Personne(String numeroSecu, String adresse, String numeroTel, ArrayList<PlageHorraire> consomation, ArrayList<CompteurElectrique> compteursListe) {
        this.numeroSecu = numeroSecu;
        this.adresse = adresse;
        this.numeroTel = numeroTel;
        this.consomation = consomation;
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

    public ArrayList<PlageHorraire> getConsomation() {
        return consomation;
    }

    public void setConsomation(ArrayList<PlageHorraire> consomation) {
        this.consomation = consomation;
    }

    public ArrayList<CompteurElectrique> getCompteursListe() {
        return compteursListe;
    }

    public void setCompteursListe(ArrayList<CompteurElectrique> compteursListe) {
        this.compteursListe = compteursListe;
    }
}
