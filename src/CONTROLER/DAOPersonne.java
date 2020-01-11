package CONTROLER;

import MODEL.Personne;

import java.util.NoSuchElementException;

public class DAOPersonne {

    public void create(Personne personne){
        main.em.getTransaction().begin();
        main.em.persist(personne);
        main.em.getTransaction().commit();
    }

    public void update(Personne personne){
        main.em.getTransaction().begin();
        Personne personneAModifier = find(personne);
        personneAModifier.setAdresse(personne.getAdresse());
        personneAModifier.setCompteursListe(personne.getCompteursListe());
        personneAModifier.setNumeroSecuriteSocial(personne.getNumeroSecuriteSocial());
        personneAModifier.setNumeroTelephone(personne.getNumeroTelephone());
        main.em.getTransaction().commit();
    }

    public void delete(Personne personne){
        main.em.getTransaction().begin();
        Personne personneASupprimer = find(personne);
        main.em.remove(personneASupprimer);
        main.em.getTransaction().commit();
    }

    public Personne find(Personne personne){
        Personne personneToFind = main.em.find(Personne.class,personne.getID());

        if(personneToFind == null)
            throw new NoSuchElementException("L'objet Personne est introuvable dans la base de données");

        return personneToFind;
    }
}
