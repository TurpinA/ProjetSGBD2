package CONTROLLER;

import MODEL.Tarif;

public class TarifActionBD {

    public static void create(Tarif tarif){
        main.em.getTransaction().begin();
        main.em.persist(tarif);
        main.em.getTransaction().commit();
    }

    public static void update(Tarif tarif){

    }

    public static void delete(Tarif tarif){

    }

    public static Tarif find(Tarif tarif){
        Tarif tarif1 = main.em.find(Tarif.class,tarif.getID());

        return tarif1;
    }
}
