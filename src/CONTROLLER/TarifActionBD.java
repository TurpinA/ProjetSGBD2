package CONTROLLER;

import MODEL.Tarif;

public class TarifActionBD {

    public static void create(Tarif tarif){
        main.em.getTransaction();
        main.em.persist(tarif);
        main.em.getTransaction().commit();
    }

    public static void update(Tarif tarif){

    }

    public static void delete(Tarif tarif){

    }

    public static void find(Tarif tarif){

    }
}
