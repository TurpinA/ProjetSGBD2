package CONTROLER;

import MODEL.Personne;
import MODEL.Tarif;

import javax.persistence.Query;
import java.util.List;
import java.util.NoSuchElementException;

public class DAOTarif {

    public void create(Tarif tarif){
        main.em.getTransaction().begin();
        main.em.persist(tarif);
        main.em.getTransaction().commit();
    }

    public void update(Tarif tarif){
        main.em.getTransaction().begin();
        Tarif tarifToUpdate = find(tarif);
        tarifToUpdate.setCode(tarif.getCode());
        tarifToUpdate.setPrix(tarif.getPrix());
        tarifToUpdate.setCategoriesTarif(tarif.getCategoriesTarif());
        main.em.getTransaction().commit();
    }

    public void delete(Tarif tarif){
        main.em.getTransaction().begin();
        Tarif tarifASupprimer = find(tarif);
        main.em.remove(tarifASupprimer);
        main.em.getTransaction().commit();
    }

    public Tarif find(Tarif tarif){
        Tarif tarifToFind = main.em.find(Tarif.class,tarif.getID());

        if(tarifToFind == null)
            throw new NoSuchElementException("L'objet Tarif est introuvable dans la base de données");

        return tarifToFind;
    }

    public List<Tarif> getAll(){
        main.em.getTransaction().begin();
        String hql = "FROM Tarif";
        Query query = main.em.createQuery(hql);
        List<Tarif> listPersonne = query.getResultList();
        main.em.getTransaction().commit();
        return listPersonne;
    }

    public List<Tarif> getAllPlein(){
        main.em.getTransaction().begin();
        String hql = "FROM Tarif tarif WHERE tarif.categoriesTarif = 0";
        Query query = main.em.createQuery(hql);
        List<Tarif> listPersonne = query.getResultList();
        main.em.getTransaction().commit();
        return listPersonne;
    }

    public List<Tarif> getAllCreux(){
        main.em.getTransaction().begin();
        String hql = "FROM Tarif tarif WHERE tarif.categoriesTarif = 1";
        Query query = main.em.createQuery(hql);
        List<Tarif> listPersonne = query.getResultList();
        main.em.getTransaction().commit();
        return listPersonne;
    }
}
