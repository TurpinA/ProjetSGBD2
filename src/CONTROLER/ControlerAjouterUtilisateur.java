package CONTROLER;

import MODEL.Personne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControlerAjouterUtilisateur {

    @FXML   private TextField numSecu;
    @FXML   private TextField adresse;
    @FXML   private TextField numTel;

    @FXML   private Button ajouterButton;
    @FXML   private Button annulerButton;

    @FXML
    public void initialize() throws SQLException {
    }

    public void ajouter(ActionEvent actionEvent) {
        boolean valide = testChamp();
        if(valide) {
            DAOPersonne daoPersonne = new DAOPersonne();

            Personne personneAAjouter = new Personne(numSecu.getText(), adresse.getText(), numTel.getText(), null);
            daoPersonne.create(personneAAjouter);

            Stage stage = (Stage) ajouterButton.getScene().getWindow();
            stage.close();
        }
    }

    public void annuler(ActionEvent actionEvent) {
        Stage stage = (Stage) annulerButton.getScene().getWindow();
        stage.close();
    }

    public boolean testChamp(){
        boolean valide = true;

        if(numSecu.getText().isEmpty())
        {
            numSecu.setStyle("-fx-border-color: red ;");
            valide = false;
        }
        else
            numSecu.setStyle("");

        if(adresse.getText().isEmpty())
        {
            adresse.setStyle("-fx-border-color: red ;");
            valide = false;
        }
        else
            adresse.setStyle("");

        if(numTel.getText().isEmpty())
        {
            numTel.setStyle("-fx-border-color: red ;");
            valide = false;
        }
        else
            numTel.setStyle("");

        return valide;
    }
}
