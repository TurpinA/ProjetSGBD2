package VIEW;

import CONTROLER.DAOCompteurElectrique;
import CONTROLER.DAOPlageHoraire;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;

public class ControlerRechercheConsomation {

    @FXML   private TextField numCompteur;
    @FXML   private DatePicker date;

    @FXML   private TextField consomation;
    @FXML   private TextField prix;
    @FXML   private TextField tarifPleins;
    @FXML   private TextField tarifCreux;

    public void ListUtiButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ListeUtilisateur.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void listCompButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ListeCompteur.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void plageConsomationButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ListePlageConsomation.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void listTarifButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ListeTarifs.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void rechercheTarifButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/RechercheParTarif.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void rechercheConsomationButton(ActionEvent actionEvent) {
    }

    public void rechercherButton(ActionEvent actionEvent) throws ParseException {
        DAOPlageHoraire daoPlageHoraire = new DAOPlageHoraire();
        DAOCompteurElectrique daoCompteurElectrique = new DAOCompteurElectrique();

        Double[] result = daoPlageHoraire.getFromCompteurAndDate(daoCompteurElectrique.findNum(numCompteur.getText()),date.getValue());

        if(result[0] != 0.0 && result[1] != 0.0)
        {
            consomation.setText(String.valueOf(result[0]));
            prix.setText(String.valueOf(result[1]));
        }
        else
        {
            consomation.setText("0");
            prix.setText("0");
        }
    }
}
