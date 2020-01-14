package CONTROLER;

import MODEL.CompteurElectrique;
import MODEL.Personne;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

public class ControlerListeUtilisateur {

    @FXML   private TableView<Personne> tableView;
    @FXML   private TableColumn<Personne,String> idColumn;
    @FXML   private TableColumn<Personne,String> numSecuColumn;
    @FXML   private TableColumn<Personne,String> adresseColumn;
    @FXML   private TableColumn<Personne, String> numTelColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<Personne, String>("ID"));
        numSecuColumn.setCellValueFactory(new PropertyValueFactory<Personne, String>("numeroSecuriteSocial"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<Personne, String>("adresse"));
        numTelColumn.setCellValueFactory(new PropertyValueFactory<Personne, String>("numeroTelephone"));

        DAOPersonne daoPersonne = new DAOPersonne();
        tableView.getItems().setAll(daoPersonne.getAll());
    }

    public void ListUtiButton(ActionEvent actionEvent) {
    }

    public void listCompButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ListeCompteur.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void tarifbutton(ActionEvent actionEvent) {
    }

    public void ajouterButton(ActionEvent actionEvent) {
        Parent root;
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/AjouterUtilisateur.fxml").toURI().toURL());
            root = fxmlLoader.load();
            stage.setTitle("Ajouter un utilisateur");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        stage.setOnHiding(event -> {
            DAOPersonne daoPersonne = new DAOPersonne();
            tableView.getItems().setAll(daoPersonne.getAll());
        });
    }

    public void modifierButton(ActionEvent actionEvent) {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            ControlerModifierUtilisateur.personneSelectionne = tableView.getSelectionModel().getSelectedItem();

            Parent root;
            Stage stage = new Stage();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ModifierUtilisateur.fxml").toURI().toURL());
                root = fxmlLoader.load();
                stage.setTitle("Modifier un utilisateur");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            stage.setOnHiding(event -> {
                DAOPersonne daoPersonne = new DAOPersonne();
                tableView.getItems().setAll(daoPersonne.getAll());
            });
        }
    }

    public void supprimerButton(ActionEvent actionEvent) {
        Personne personneASupprimer = tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Etes-vous sur de vouloir supprimer cette personne : sécu N°" + personneASupprimer.getNumeroSecuriteSocial() + " ?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            DAOPersonne daoPersonne = new DAOPersonne();
            daoPersonne.delete(personneASupprimer);
            tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
        }
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

    public void rechercheConsomationButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/RechercheConsomation.fxml").toURI().toURL());
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
}
