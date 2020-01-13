package CONTROLER;

import MODEL.CompteurElectrique;
import MODEL.Personne;

import javax.persistence.Query;
import java.util.List;
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

    public List<Personne> getAll(){
        main.em.getTransaction().begin();
        String hql = "FROM Personne";
        Query query = main.em.createQuery(hql);
        List<Personne> listPersonne = query.getResultList();
        main.em.getTransaction().commit();
        return listPersonne;
    }
}
