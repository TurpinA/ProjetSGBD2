package CONTROLLER;

import MODEL.Tarif;

import java.util.NoSuchElementException;

public class TarifActionBD {

    public static void create(Tarif tarif){
        main.em.getTransaction().begin();
        main.em.persist(tarif);
        main.em.getTransaction().commit();
    }

    public static void update(Tarif tarif){
        main.em.getTransaction().begin();
        Tarif tarifAModifier = find(tarif);
        tarifAModifier = tarif;
        main.em.getTransaction().commit();
    }

    public static void delete(Tarif tarif){
        main.em.getTransaction().begin();
        Tarif tarifASupprimer = find(tarif);
        main.em.remove(tarifASupprimer);
        main.em.getTransaction().commit();
    }

    public static Tarif find(Tarif tarif){
        Tarif tarif1 = main.em.find(Tarif.class,tarif.getID());

        if(tarif1 == null)
            throw new NoSuchElementException("L'objet Tarif est introuvable dans la base de données");

        return tarif1;
    }
}
