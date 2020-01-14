package CONTROLER;

import MODEL.CategoriesTarif;
import MODEL.CompteurElectrique;
import MODEL.Personne;
import MODEL.Tarif;
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

public class ControlerAjouterTarif {

    @FXML   private TextField code;
    @FXML   private TextField prix;
    @FXML   private ComboBox<CategoriesTarif> categorie;

    @FXML   private Button ajouterButton;
    @FXML   private Button annulerButton;

    @FXML
    public void initialize() throws SQLException {
        categorie.getItems().addAll(CategoriesTarif.values());
    }

    public void ajouter(ActionEvent actionEvent) {
        if(categorie.getValue() != null) {
            categorie.setStyle("");
            DAOTarif daoTarif = new DAOTarif();

            Tarif tarifAAjouter = new Tarif();
            tarifAAjouter.setCode(code.getText());
            tarifAAjouter.setPrix(Double.valueOf(prix.getText()));
            tarifAAjouter.setCategoriesTarif(categorie.getValue());

            daoTarif.create(tarifAAjouter);

            Stage stage = (Stage) ajouterButton.getScene().getWindow();
            stage.close();
        }
        else
        {
            categorie.setStyle("-fx-border-color: red ;");
        }
    }

    public void annuler(ActionEvent actionEvent) {
        Stage stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }
}
