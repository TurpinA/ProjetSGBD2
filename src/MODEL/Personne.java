package MODEL;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Personne {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private String numeroSecuriteSocial;
    private String adresse;
    private String numeroTelephone;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "personneAssocie")
    private List<CompteurElectrique> compteursListe;

    public Personne(){}

    public Personne(String numeroSecuriteSocial, String adresse, String numeroTelephone, ArrayList<CompteurElectrique> compteursListe) {
        this.numeroSecuriteSocial = numeroSecuriteSocial;
        this.adresse = adresse;
        this.numeroTelephone = numeroTelephone;
        this.compteursListe = compteursListe;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNumeroSecuriteSocial() {
        return numeroSecuriteSocial;
    }

    public void setNumeroSecuriteSocial(String NumeroSecuriteSocial) {
        this.numeroSecuriteSocial = NumeroSecuriteSocial;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNumeroTelephone() {
        return numeroTelephone;
    }

    public void setNumeroTelephone(String numeroTel) {
        this.numeroTelephone = numeroTel;
    }

    public List<CompteurElectrique> getCompteursListe() {
        return compteursListe;
    }

    public void setCompteursListe(List<CompteurElectrique> compteursListe) {
        this.compteursListe = compteursListe;
    }
}
