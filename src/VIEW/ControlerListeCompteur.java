package VIEW;

import CONTROLER.DAOCompteurElectrique;
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

public class ControlerListeCompteur {

    @FXML   private TableView<CompteurElectrique> tableView;
    @FXML   private TableColumn<CompteurElectrique,String> idColumn;
    @FXML   private TableColumn<CompteurElectrique,String> numComtpeurColumn;
    @FXML   private TableColumn<CompteurElectrique,String> adresseColumn;
    @FXML   private TableColumn<CompteurElectrique, Date> dateColumn;
    @FXML   private TableColumn<CompteurElectrique, Personne> personneColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<CompteurElectrique, String>("ID"));
        numComtpeurColumn.setCellValueFactory(new PropertyValueFactory<CompteurElectrique, String>("numeroCompteur"));
        adresseColumn.setCellValueFactory(new PropertyValueFactory<CompteurElectrique, String>("adresse"));

        dateColumn.setCellFactory(column -> {
            TableCell<CompteurElectrique, Date> cell = new TableCell<CompteurElectrique, Date>() {
                private SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");

                @Override
                protected void updateItem(Date item, boolean empty) {
                    super.updateItem(item, empty);
                    if(empty) {
                        setText(null);
                    }
                    else {
                        this.setText(format.format(item));

                    }
                }
            };

            return cell;
        });

        dateColumn.setCellValueFactory(new PropertyValueFactory<CompteurElectrique, Date>("dateActivation"));
        personneColumn.setCellValueFactory(new PropertyValueFactory<CompteurElectrique, Personne>("personneAssocie"));

        DAOCompteurElectrique DAOCompteurElectrique = new DAOCompteurElectrique();
        tableView.getItems().setAll(DAOCompteurElectrique.getAll());
    }

    public void listUtilisateurButton(ActionEvent actionEvent) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ListeUtilisateur.fxml").toURI().toURL());
        Parent root = fxmlLoader.load();
        Scene scene = new Scene(root);
        Stage window = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        window.setScene(scene);
        window.show();
    }

    public void listCompteurButton(ActionEvent actionEvent) {
    }

    public void ajouterButton(ActionEvent actionEvent) {
        Parent root;
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/AjouterCompteur.fxml").toURI().toURL());
            root = fxmlLoader.load();
            stage.setTitle("Ajouter un compteur");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        stage.setOnHiding(event -> {
            DAOCompteurElectrique DAOCompteurElectrique = new DAOCompteurElectrique();
            tableView.getItems().setAll(DAOCompteurElectrique.getAll());
        });
    }

    public void modifierButton(ActionEvent actionEvent) {
        if(tableView.getSelectionModel().getSelectedItem() != null) {
            ControlerModifierCompteur.compteurSelectione = tableView.getSelectionModel().getSelectedItem();

            Parent root;
            Stage stage = new Stage();
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/ModifierCompteur.fxml").toURI().toURL());
                root = fxmlLoader.load();
                stage.setTitle("Modifier un compteur");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }

            stage.setOnHiding(event -> {
                DAOCompteurElectrique DAOCompteurElectrique = new DAOCompteurElectrique();
                tableView.getItems().setAll(DAOCompteurElectrique.getAll());
            });
        }
    }

    public void supprimerButton(ActionEvent actionEvent) {
        CompteurElectrique compteurElectriqueASupprimer = tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Etes-vous sur de vouloir supprimer le compteur : N°" + compteurElectriqueASupprimer.getNumeroCompteur() + " ?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            DAOCompteurElectrique DAOCompteur = new DAOCompteurElectrique();
            DAOCompteur.delete(compteurElectriqueASupprimer);
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
