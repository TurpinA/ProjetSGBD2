package CONTROLLER;

import MODEL.Personne;
import MODEL.Tarif;

import java.util.NoSuchElementException;

public class PersonneActionBD {

    public static void create(Personne personne){
        main.em.getTransaction().begin();
        main.em.persist(personne);
        main.em.getTransaction().commit();
    }

    public static void update(Personne personne){
        main.em.getTransaction().begin();
        Personne personneAModifier = find(personne);
        personneAModifier.setAdresse(personne.getAdresse());
        personneAModifier.setCompteursListe(personne.getCompteursListe());
        personneAModifier.setNumeroSecu(personne.getNumeroSecu());
        personneAModifier.setNumeroTel(personne.getNumeroTel());
        main.em.getTransaction().commit();
    }

    public static void delete(Personne personne){
        main.em.getTransaction().begin();
        Personne personneASupprimer = find(personne);
        main.em.remove(personneASupprimer);
        main.em.getTransaction().commit();
    }

    public static Personne find(Personne personne){
        Personne personne1 = main.em.find(Personne.class,personne.getID());

        if(personne1 == null)
            throw new NoSuchElementException("L'objet Personne est introuvable dans la base de données");

        return personne1;
    }
}
