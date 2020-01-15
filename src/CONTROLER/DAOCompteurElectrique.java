package CONTROLER;

import MODEL.CompteurElectrique;
import MODEL.Tarif;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class DAOCompteurElectrique {

    public void create(CompteurElectrique compteurElectrique){
        main.em.getTransaction().begin();
        main.em.persist(compteurElectrique);
        main.em.getTransaction().commit();
    }

    public void update(CompteurElectrique compteurElectrique){
        main.em.getTransaction().begin();
        CompteurElectrique compteurElectriqueToUpdate = find(compteurElectrique);
        compteurElectriqueToUpdate.setAdresse(compteurElectrique.getAdresse());
        compteurElectriqueToUpdate.setConsommation(compteurElectrique.getConsommation());
        compteurElectriqueToUpdate.setDateActivation(compteurElectrique.getDateActivation());
        compteurElectriqueToUpdate.setNumeroCompteur(compteurElectrique.getNumeroCompteur());
        compteurElectriqueToUpdate.setPersonneAssocie(compteurElectrique.getPersonneAssocie());
        main.em.getTransaction().commit();
    }

    public void delete(CompteurElectrique compteurElectrique){
        main.em.getTransaction().begin();
        CompteurElectrique compteurElectriqueToUpdate = find(compteurElectrique);
        main.em.remove(compteurElectriqueToUpdate);
        main.em.getTransaction().commit();
    }

    public CompteurElectrique find(CompteurElectrique compteurElectrique){
        CompteurElectrique compteurElectriqueToUpdate = main.em.find(CompteurElectrique.class,compteurElectrique.getID());

        if(compteurElectriqueToUpdate == null)
            throw new NoSuchElementException("L'objet CompteurElectrique est introuvable dans la base de données");

        return compteurElectriqueToUpdate;
    }

    public List<CompteurElectrique> getAll(){

        main.em.getTransaction().begin();
        String hql = "FROM CompteurElectrique";
        Query query = main.em.createQuery(hql);
        List<CompteurElectrique> listCompteur = query.getResultList();
        main.em.getTransaction().commit();
        return listCompteur;
    }

    public CompteurElectrique findNum(String numCompteur){
        main.em.getTransaction().begin();
        String hql = "FROM CompteurElectrique compteur where compteur.numeroCompteur = " + numCompteur;
        Query query = main.em.createQuery(hql);
        CompteurElectrique compteurElectriqueToFind = (CompteurElectrique) query.getSingleResult();
        main.em.getTransaction().commit();

        if(compteurElectriqueToFind == null)
            throw new NoSuchElementException("L'objet Tarif est introuvable dans la base de données");

        return compteurElectriqueToFind;
    }
}
