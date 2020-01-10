package MODEL;

import javax.persistence.*;

@Entity
public class Tarif {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private String code;
    private Double prix;
}
