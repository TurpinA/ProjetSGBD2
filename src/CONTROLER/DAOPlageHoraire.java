package CONTROLER;

import MODEL.Personne;
import MODEL.PlageHoraire;

import javax.persistence.Query;
import java.util.List;
import java.util.NoSuchElementException;

public class DAOPlageHoraire {

    public void create(PlageHoraire plageHoraireBD){
        main.em.getTransaction().begin();
        main.em.persist(plageHoraireBD);
        main.em.getTransaction().commit();
    }

    public void update(PlageHoraire plageHoraireBD){
        main.em.getTransaction().begin();
        PlageHoraire plageHoraireBDToFind = find(plageHoraireBD);
        plageHoraireBDToFind.setCompteur(plageHoraireBD.getCompteur());
        plageHoraireBDToFind.setDate(plageHoraireBD.getDate());
        plageHoraireBDToFind.setHeureDebut(plageHoraireBD.getHeureDebut());
        plageHoraireBDToFind.setHeureFin(plageHoraireBD.getHeureFin());
        plageHoraireBDToFind.setPuissanceConsommee(plageHoraireBD.getPuissanceConsommee());
        main.em.getTransaction().commit();
    }

    public void delete(PlageHoraire plageHoraireBD){
        main.em.getTransaction().begin();
        PlageHoraire plageHoraireBDToDelete = find(plageHoraireBD);
        main.em.remove(plageHoraireBDToDelete);
        main.em.getTransaction().commit();
    }

    public PlageHoraire find(PlageHoraire plageHoraire) throws NoSuchElementException{
        PlageHoraire plageHoraireBD = main.em.find(PlageHoraire.class, plageHoraire.getID());

        if(plageHoraireBD == null)
            throw new NoSuchElementException("L'objet PlageHoraire est introuvable dans la base de données");

        return plageHoraireBD;
    }

    public List<PlageHoraire> getAll(){
        main.em.getTransaction().begin();
        String hql = "FROM PlageHoraire";
        Query query = main.em.createQuery(hql);
        List<PlageHoraire> listPlageHorraire = query.getResultList();
        main.em.getTransaction().commit();
        return listPlageHorraire;
    }

}
