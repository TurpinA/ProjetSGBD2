package CONTROLLER;

import MODEL.PlageHoraire;
import MODEL.Tarif;

import java.util.NoSuchElementException;

public class DAOPlageHoraire {

    public static void create(PlageHoraire plageHoraireBD){
        main.em.getTransaction().begin();
        main.em.persist(plageHoraireBD);
        main.em.getTransaction().commit();
    }

    public static void update(PlageHoraire plageHoraireBD){
        main.em.getTransaction().begin();
        PlageHoraire plageHoraireBDToFind = find(plageHoraireBD);
        plageHoraireBDToFind = plageHoraireBD;
        main.em.getTransaction().commit();
    }

    public static void delete(PlageHoraire plageHoraireBD){
        main.em.getTransaction().begin();
        PlageHoraire plageHoraireBDToDelete = find(plageHoraireBD);
        main.em.remove(plageHoraireBDToDelete);
        main.em.getTransaction().commit();
    }

    public static PlageHoraire find(PlageHoraire plageHoraire){
        PlageHoraire plageHoraireBD = main.em.find(PlageHoraire.class, plageHoraire.getID());

        if(plageHoraireBD == null)
            throw new NoSuchElementException("L'objet Tarif est introuvable dans la base de données");

        return plageHoraireBD;
    }

}
