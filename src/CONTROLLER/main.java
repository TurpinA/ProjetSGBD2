package CONTROLLER;

import MODEL.Tarif;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class main {

	public static EntityManagerFactory entityManagerFactory;
	public static EntityManager em;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("test");
		em = entityManagerFactory.createEntityManager();

		Tarif test2 = new Tarif("DEBUT",8.25);

		TarifActionBD.create(test2);
		test2.setCode("FIN");
		TarifActionBD.update(test2);
	}

}
