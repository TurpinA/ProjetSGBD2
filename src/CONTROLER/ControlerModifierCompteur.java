package CONTROLER;

import MODEL.CompteurElectrique;
import MODEL.Personne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;
import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ControlerModifierCompteur {

    public static CompteurElectrique compteurSelectione;

    @FXML   private TextField numCompteur;
    @FXML   private TextField adresse;
    @FXML   private DatePicker dateActivation;
    @FXML   private ComboBox<Personne> personneAssocie;

    @FXML   private Button modifierButton;
    @FXML   private Button annulerButton;

    @FXML
    public void initialize() throws SQLException {
        DAOPersonne daoPersonne = new DAOPersonne();
        personneAssocie.getItems().addAll(daoPersonne.getAll());

        numCompteur.setText(compteurSelectione.getNumeroCompteur());
        adresse.setText(compteurSelectione.getAdresse());
        dateActivation.setValue(compteurSelectione.getDateActivation().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        personneAssocie.setValue(compteurSelectione.getPersonneAssocie());
    }

    public void modifier(ActionEvent actionEvent) {
        DAOCompteurElectrique daoCompteurElectrique = new DAOCompteurElectrique();

        CompteurElectrique competeurAAjouter = new CompteurElectrique();
        competeurAAjouter.setID(compteurSelectione.getID());
        competeurAAjouter.setAdresse(adresse.getText());

        LocalDate localDate = dateActivation.getValue();
        Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.of("UTC")));
        Date date = Date.from(instant);
        competeurAAjouter.setDateActivation(date);

        competeurAAjouter.setNumeroCompteur(numCompteur.getText());
        competeurAAjouter.setPersonneAssocie(personneAssocie.getValue());

        daoCompteurElectrique.update(competeurAAjouter);

        Stage stage = (Stage) modifierButton.getScene().getWindow();
        stage.close();
    }

    public void annuler(ActionEvent actionEvent) {
        Stage stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }
}
