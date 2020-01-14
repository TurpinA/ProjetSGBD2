package CONTROLER;

import MODEL.CompteurElectrique;
import MODEL.Personne;
import MODEL.PlageHoraire;
import MODEL.Tarif;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
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
import java.util.regex.Pattern;

public class ControlerAjouterPlageConsomation {

    @FXML   private TextField consomation;

    @FXML   private DatePicker date;

    @FXML   private JFXTimePicker heureDebut;
    @FXML   private JFXTimePicker heureFin;

    @FXML   private ComboBox<CompteurElectrique> numeroCompteur;
    @FXML   private ComboBox<Tarif> tarifPlein;
    @FXML   private ComboBox<Tarif> tarifCreux;

    @FXML   private Button ajouterButton;
    @FXML   private Button annulerButton;

    @FXML
    public void initialize() throws SQLException {
        heureDebut.setEditable(false);
        heureFin.setEditable(false);

        DAOTarif daoTarif = new DAOTarif();
        DAOCompteurElectrique daoCompteurElectrique = new DAOCompteurElectrique();

        numeroCompteur.getItems().addAll(daoCompteurElectrique.getAll());
        tarifPlein.getItems().addAll(daoTarif.getAllPlein());
        tarifCreux.getItems().addAll(daoTarif.getAllCreux());
    }

    public void annuler(ActionEvent actionEvent) {
        Stage stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }

    public void ajouter(ActionEvent actionEvent) {
        boolean valide = testChamp();
        if(valide) {
            DAOPlageHoraire daoPlageHoraire = new DAOPlageHoraire();

            PlageHoraire plageHoraireAAjouter = new PlageHoraire();
            plageHoraireAAjouter.setCompteur(numeroCompteur.getValue());

            LocalDate localDate = date.getValue();
            Instant instant = Instant.from(localDate.atStartOfDay(ZoneId.of("UTC")));
            Date date = Date.from(instant);
            plageHoraireAAjouter.setDate(date);

            plageHoraireAAjouter.setHeureDebut(heureDebut.getValue());
            plageHoraireAAjouter.setHeureFin(heureFin.getValue());
            plageHoraireAAjouter.setPuissanceConsommee(Double.valueOf(consomation.getText()));

            plageHoraireAAjouter.getRelationTarifPlageHoraires().addAll(FactoryRelationTarifPlageHoraire.CreateFromPlageHoraire(plageHoraireAAjouter,tarifCreux.getValue(),tarifPlein.getValue()));

            daoPlageHoraire.create(plageHoraireAAjouter);

            Stage stage = (Stage) ajouterButton.getScene().getWindow();
            stage.close();
        }
    }

    public boolean testChamp(){
        boolean valide = true;

        if(numeroCompteur.getValue() == null)
        {
            numeroCompteur.setStyle("-fx-border-color: red ;");
            valide = false;
        }
        else
            numeroCompteur.setStyle("");

        if(date.getValue() == null)
        {
            date.setStyle("-fx-border-color: red ;");
            valide = false;
        }
        else
            date.setStyle("");

        if(heureDebut.getValue() == null)
        {
            heureDebut.setStyle("-fx-border-color: red ;");
            valide = false;
        }
        else
            heureDebut.setStyle("");

        if(heureFin.getValue() == null)
        {
            heureFin.setStyle("-fx-border-color: red ;");
            valide = false;
        }
        else
            heureFin.setStyle("");

        if(!Pattern.matches("[0-9\\.]++$", consomation.getText()) || consomation.getText().isEmpty()) {
            consomation.setStyle("-fx-border-color: red ;");
            valide = false;
        }
        else {
            try {
                Double.parseDouble(consomation.getText());
                consomation.setStyle("");
            }
            catch(NumberFormatException e)
            {
                consomation.setStyle("-fx-border-color: red ;");
                valide = false;
            }
        }

        if(tarifPlein.getValue() == null)
        {
            tarifPlein.setStyle("-fx-border-color: red ;");
            valide = false;
        }
        else
            tarifPlein.setStyle("");

        if(tarifCreux.getValue() == null)
        {
            tarifCreux.setStyle("-fx-border-color: red ;");
            valide = false;
        }
        else
            tarifCreux.setStyle("");

        return valide;
    }
}
