package CONTROLER;

import MODEL.RelationTarifPlageHoraire;
import MODEL.Tarif;

import javax.persistence.Query;
import java.util.List;
import java.util.NoSuchElementException;

public class DAORelationTarifPlageHoraire {

    public void create(RelationTarifPlageHoraire relationTarifPlageHoraire){
        main.em.getTransaction().begin();
        main.em.persist(relationTarifPlageHoraire);
        main.em.getTransaction().commit();
    }

    public void delete(RelationTarifPlageHoraire relationTarifPlageHoraire){
        main.em.getTransaction().begin();
        RelationTarifPlageHoraire relationTarifPlageHoraireASupprimer = find(relationTarifPlageHoraire);
        main.em.remove(relationTarifPlageHoraireASupprimer);
        main.em.getTransaction().commit();
    }

    public RelationTarifPlageHoraire find(RelationTarifPlageHoraire relationTarifPlageHoraire){
        RelationTarifPlageHoraire relationTarifPlageHoraireToFind = main.em.find(RelationTarifPlageHoraire.class,relationTarifPlageHoraire.getPlageHoraire_Tarif_ID());

        if(relationTarifPlageHoraireToFind == null)
            throw new NoSuchElementException("L'objet RelationTarifPlageHoraire est introuvable dans la base de données");

        return relationTarifPlageHoraireToFind;
    }

    public List<RelationTarifPlageHoraire> getAll(){
        main.em.getTransaction().begin();
        String hql = "FROM RelationTarifPlageHoraire";
        Query query = main.em.createQuery(hql);
        List<RelationTarifPlageHoraire> listRelationTarifPlageHoraire = query.getResultList();
        main.em.getTransaction().commit();
        return listRelationTarifPlageHoraire;
    }
}
