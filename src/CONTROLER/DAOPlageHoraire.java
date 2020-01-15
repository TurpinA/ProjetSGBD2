package CONTROLER;

import MODEL.CompteurElectrique;
import MODEL.PlageHoraire;
import MODEL.RelationTarifPlageHoraire;

import javax.persistence.Query;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.*;

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

    public Double[] getFromCompteurAndDate(CompteurElectrique compteur, LocalDate date) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.clear();
        calendar.set(date.getYear(), date.getMonthValue()-1, date.getDayOfMonth());
        calendar.set(Calendar.HOUR, 1);
        Date dateGood = calendar.getTime();

        main.em.getTransaction().begin();
        String hql = "FROM PlageHoraire plage where plage.Compteur.numeroCompteur = :compteurNum and plage.date = :date";
        Query query = main.em.createQuery(hql);
        query.setParameter("compteurNum",compteur.getNumeroCompteur());
        query.setParameter("date",dateGood);
        List<PlageHoraire> listPlageHoraire = query.getResultList();
        main.em.getTransaction().commit();

        Double puissanceconsommer = 0.0;
        Double prix = 0.0;

        for (PlageHoraire plage : listPlageHoraire) {
            for (RelationTarifPlageHoraire relation : plage.getRelationTarifPlageHoraires()) {
                puissanceconsommer += relation.getConsommation();
                prix = prix + relation.getConsommation()*relation.getTarif().getPrix();
            }
        }

        Double [] result = {puissanceconsommer, prix};

        return result;
    }

}
