package MODEL;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class PlageHoraire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private Date date;
    private LocalTime heureDebut;
    private LocalTime heureFin;
    private Double puissanceConsommee;

    @ManyToOne
    private CompteurElectrique Compteur;

    @OneToMany(mappedBy = "plageHoraire")
    private Set<RelationTarifPlageHoraire> relationTarifPlageHoraires = new HashSet<>();

    public PlageHoraire(){

    }

    public PlageHoraire(Date date, LocalTime heureDebut, LocalTime heureFin, Double puissanceConsomme, CompteurElectrique compteur) {
        this.date = date;
        this.heureDebut = heureDebut;
        this.heureFin = heureFin;
        this.puissanceConsommee = puissanceConsomme;
        this.Compteur = compteur;
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

    public Double getPuissanceConsommee() {
        return puissanceConsommee;
    }

    public void setPuissanceConsommee(Double puissanceConsomme) {
        this.puissanceConsommee = puissanceConsomme;
    }

    public CompteurElectrique getCompteur() {
        return Compteur;
    }

    public void setCompteur(CompteurElectrique compteur) {
        this.Compteur = compteur;
    }

    public Set<RelationTarifPlageHoraire> getRelationTarifPlageHoraires() {
        return relationTarifPlageHoraires;
    }

    public void setRelationTarifPlageHoraires(Set<RelationTarifPlageHoraire> relationTarifPlageHoraires) {
        this.relationTarifPlageHoraires = relationTarifPlageHoraires;
    }
}
