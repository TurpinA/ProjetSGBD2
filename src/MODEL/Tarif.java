package MODEL;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Tarif {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private String code;
    private Double prix;
    private CategoriesTarif categoriesTarif;

    @OneToMany(mappedBy = "tarif")
    private Set<RelationTarifPlageHoraire> relationTarifPlageHoraires = new HashSet<>();

    public Tarif(){

    }

    public Tarif(String code, Double prix,CategoriesTarif categoriesTarif) {
        this.code = code;
        this.prix = prix;
        this.categoriesTarif = categoriesTarif;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Double getPrix() {
        return prix;
    }

    public void setPrix(Double prix) {
        this.prix = prix;
    }

    public CategoriesTarif getCategoriesTarif() {
        return categoriesTarif;
    }

    public void setCategoriesTarif(CategoriesTarif categoriesTarif) {
        this.categoriesTarif = categoriesTarif;
    }

    public Set<RelationTarifPlageHoraire> getRelationTarifPlageHoraires() {
        return relationTarifPlageHoraires;
    }

    public void setRelationTarifPlageHoraires(Set<RelationTarifPlageHoraire> relationTarifPlageHoraires) {
        this.relationTarifPlageHoraires = relationTarifPlageHoraires;
    }

    @Override
    public String toString(){
        return String.valueOf(code);
    }
}
