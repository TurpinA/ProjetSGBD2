package CONTROLER;

import MODEL.CompteurElectrique;
import MODEL.Personne;
import MODEL.Tarif;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTimePicker;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.sql.SQLException;

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
        DAOTarif daoTarif = new DAOTarif();
        DAOCompteurElectrique daoCompteurElectrique = new DAOCompteurElectrique();

        numeroCompteur.getItems().addAll(daoCompteurElectrique.getAll());
        tarifPlein.getItems().addAll(daoTarif.getAllPlein());
        tarifCreux.getItems().addAll(daoTarif.getAllCreux());
    }

    public void annuler(ActionEvent actionEvent) {
    }

    public void ajouter(ActionEvent actionEvent) {
    }
}
