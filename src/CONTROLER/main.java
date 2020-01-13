package CONTROLER;

import MODEL.CompteurElectrique;
import MODEL.Personne;
import MODEL.Tarif;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.io.File;
import java.sql.Date;
import java.time.LocalTime;
import java.util.ArrayList;

public class main extends Application {

	public static EntityManagerFactory entityManagerFactory;
	public static EntityManager em;

	public static void main(String[] args) {
		entityManagerFactory = Persistence.createEntityManagerFactory("bd2");
		em = entityManagerFactory.createEntityManager();

		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception{
		FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ListeCompteur.fxml").toURI().toURL());
		Parent root = fxmlLoader.load();
		primaryStage.setTitle("Tarification Manager");
		primaryStage.setScene(new Scene(root));
		primaryStage.show();
	}

}
