package CONTROLER;

import MODEL.Tarif;

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
}
