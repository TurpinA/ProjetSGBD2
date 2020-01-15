package CONTROLER;

import MODEL.PlageHoraire;
import MODEL.RelationTarifPlageHoraire;
import MODEL.Tarif;
import javafx.util.Pair;

import javax.persistence.Query;
import java.util.Iterator;
import java.util.List;

public class Recherche {

    public static List<RelationTarifPlageHoraire> rechercheParTarif(Tarif tarif) {
        main.em.getTransaction().begin();
        String hql = "FROM RelationTarifPlageHoraire relation WHERE relation.tarif.ID = " + tarif.getID();
        Query query = main.em.createQuery(hql);
        List<RelationTarifPlageHoraire> result = query.getResultList();
        main.em.getTransaction().commit();
        return result;
    }
}
