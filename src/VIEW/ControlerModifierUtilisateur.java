package VIEW;

import CONTROLER.DAOPersonne;
import MODEL.Personne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.SQLException;

public class ControlerModifierUtilisateur {

    public static Personne personneSelectionne;

    @FXML   private TextField numSecu;
    @FXML   private TextField adresse;
    @FXML   private TextField numTel;

    @FXML   private Button modifierButton;
    @FXML   private Button annulerButton;

    @FXML
    public void initialize() throws SQLException {
        numSecu.setText(personneSelectionne.getNumeroSecuriteSocial());
        adresse.setText(personneSelectionne.getAdresse());
        numTel.setText(personneSelectionne.getNumeroTelephone());
    }

    public void modifier(ActionEvent actionEvent) {
        boolean valide = testChamp();
        if(valide) {
            DAOPersonne daoPersonne = new DAOPersonne();

            Personne personneAAjouter = new Personne(numSecu.getText(), adresse.getText(), numTel.getText(), null);
            personneAAjouter.setID(personneSelectionne.getID());
            daoPersonne.update(personneAAjouter);

            Stage stage = (Stage) modifierButton.getScene().getWindow();
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
