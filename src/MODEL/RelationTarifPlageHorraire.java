package MODEL;

import javax.persistence.*;

@Entity
@IdClass(RelationTarifPlageHorraireId.class)
public class RelationTarifPlageHorraire {
    @Id @ManyToOne PlageHoraire plageHoraire;

    @Id @ManyToOne Tarif tarif;

    private Double consomation;
}
