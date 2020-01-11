package MODEL;

import javax.persistence.*;

@Entity
public class Tarif {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int ID;

    private String code;
    private Double prix;

    public Tarif(){

    }

    public Tarif(String code, Double prix) {
        this.code = code;
        this.prix = prix;
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
}
