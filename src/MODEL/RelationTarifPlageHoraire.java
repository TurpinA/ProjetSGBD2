package MODEL;

import javax.persistence.*;

@Entity
public class RelationTarifPlageHoraire {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int PlageHoraire_Tarif_ID;

    @ManyToOne
    PlageHoraire plageHoraire;

    @ManyToOne
    Tarif tarif;

    private Double consommation;

    public RelationTarifPlageHoraire(){}

    public RelationTarifPlageHoraire(PlageHoraire plageHoraire,Tarif tarif, Double consommation){
        this.consommation = consommation;
        this.plageHoraire = plageHoraire;
        this.tarif = tarif;
        plageHoraire.getRelationTarifPlageHoraires().add(this);
        tarif.getRelationTarifPlageHoraires().add(this);
    }

    public Double getConsommation() {
        return consommation;
    }

    public void setConsommation(Double consommation) {
        this.consommation = consommation;
    }

    public int getPlageHoraire_Tarif_ID() {
        return PlageHoraire_Tarif_ID;
    }

    public void setPlageHoraire_Tarif_ID(int plageHoraire_Tarif_ID) {
        PlageHoraire_Tarif_ID = plageHoraire_Tarif_ID;
    }

    public PlageHoraire getPlageHoraire() {
        return plageHoraire;
    }

    public Tarif getTarif() {
        return tarif;
    }
}
