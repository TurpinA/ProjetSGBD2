package CONTROLER;

import MODEL.Tarif;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {

	public static EntityManagerFactory entityManagerFactory;
	public static EntityManager em;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("bd2");
		em = entityManagerFactory.createEntityManager();

		Tarif tariftest = new Tarif("ABD",1.3);
		DAOTarif daoTarif = new DAOTarif();
		daoTarif.create(tariftest);
		em.getTransaction().begin();
		tariftest.setCode("ABDEC");
		em.getTransaction().commit();



	}

}
