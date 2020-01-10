package MODEL;

import javax.persistence.*;

@Entity
public class PlageHorraire {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;
}
