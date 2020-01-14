package CONTROLER;

import MODEL.CategoriesTarif;
import MODEL.Tarif;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControlerModifierTarif {

    public static Tarif tarifSelectionne;

    @FXML   private TextField code;
    @FXML   private TextField prix;
    @FXML   private ComboBox<CategoriesTarif> categorie;

    @FXML   private Button modifierButton;
    @FXML   private Button annulerButton;

    @FXML
    public void initialize() throws SQLException {
        code.setText(tarifSelectionne.getCode());
        prix.setText(String.valueOf(tarifSelectionne.getPrix()));
        categorie.setValue(tarifSelectionne.getCategoriesTarif());
        categorie.getItems().addAll(CategoriesTarif.values());
    }

    public void modifier(ActionEvent actionEvent) {
        if(categorie.getValue() != null) {
            categorie.setStyle("");
            DAOTarif daoTarif = new DAOTarif();

            Tarif tarifAAjouter = new Tarif();
            tarifAAjouter.setCode(code.getText());
            tarifAAjouter.setPrix(Double.valueOf(prix.getText()));
            tarifAAjouter.setCategoriesTarif(categorie.getValue());
            tarifAAjouter.setID(tarifSelectionne.getID());

            daoTarif.update(tarifAAjouter);

            Stage stage = (Stage) modifierButton.getScene().getWindow();
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
