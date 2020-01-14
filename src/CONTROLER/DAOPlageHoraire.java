package CONTROLER;

import MODEL.CompteurElectrique;
import MODEL.Personne;
import MODEL.PlageHoraire;
import MODEL.RelationTarifPlageHoraire;

import javax.persistence.Query;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
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

    public Double[] getFromCompteurAndDate(CompteurElectrique compteur, LocalDate date){

        main.em.getTransaction().begin();
        String datehql = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + " 00:00:00";
        String hql = "from PlageHoraire as plage where plage.Compteur.numeroCompteur = "+compteur.getNumeroCompteur()+" and plage.date = "+datehql+"";
        Query query = main.em.createQuery(hql);
        @SuppressWarnings("unchecked")
        List<PlageHoraire> listPlageHoraire = query.getResultList();
        main.em.getTransaction().commit();

        Double puissanceconsommer = 0.0;
        Double prix = 0.0;

        for (PlageHoraire plage :
                listPlageHoraire) {
            for (RelationTarifPlageHoraire relation :
                    plage.getRelationTarifPlageHoraires()) {
                puissanceconsommer += relation.getConsommation();
                prix = prix + relation.getConsommation()*relation.getTarif().getPrix();
            }
        }

        Double [] result = {puissanceconsommer, prix};

        return result;
    }

}
