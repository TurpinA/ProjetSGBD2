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

    public PlageHorraire(Date date, LocalTime heureDebut, LocalTime heureFin, Double puissanceConsomme, CompteurElectrique compteur) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.puissanceConsomme = puissanceConsomme;
        this.compteur = compteur;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public LocalTime getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(LocalTime heureDebut) {
        this.heureDebut = heureDebut;
    }

    public LocalTime getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(LocalTime heureFin) {
        this.heureFin = heureFin;
    }

    public Double getPuissanceConsomme() {
        return puissanceConsomme;
    }

    public void setPuissanceConsomme(Double puissanceConsomme) {
        this.puissanceConsomme = puissanceConsomme;
    }

    public CompteurElectrique getCompteur() {
        return compteur;
    }

    public void setCompteur(CompteurElectrique compteur) {
        this.compteur = compteur;
    }
}
