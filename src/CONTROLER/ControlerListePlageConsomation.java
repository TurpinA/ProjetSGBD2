package CONTROLER;

import MODEL.CompteurElectrique;
import MODEL.Personne;
import MODEL.PlageHoraire;
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
import java.time.LocalTime;
import java.util.Date;
import java.util.Optional;

public class ControlerListePlageConsomation {

    @FXML   private TableView<PlageHoraire> tableView;
    @FXML   private TableColumn<PlageHoraire,String> idColumn;
    @FXML   private TableColumn<PlageHoraire, Date> dateColumn;
    @FXML   private TableColumn<PlageHoraire, LocalTime> debutColumn;
    @FXML   private TableColumn<PlageHoraire, LocalTime> finColumn;
    @FXML   private TableColumn<PlageHoraire,String> consomationColumn;
    @FXML   private TableColumn<PlageHoraire, CompteurElectrique> compteurColumn;

    @FXML
    public void initialize() {
        idColumn.setCellValueFactory(new PropertyValueFactory<PlageHoraire, String>("ID"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<PlageHoraire, Date>("date"));

        dateColumn.setCellFactory(column -> {
            TableCell<PlageHoraire, Date> cell = new TableCell<PlageHoraire, Date>() {
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

        debutColumn.setCellValueFactory(new PropertyValueFactory<PlageHoraire, LocalTime>("heureDebut"));
        finColumn.setCellValueFactory(new PropertyValueFactory<PlageHoraire, LocalTime>("heureFin"));
        consomationColumn.setCellValueFactory(new PropertyValueFactory<PlageHoraire, String>("puissanceConsommee"));
        compteurColumn.setCellValueFactory(new PropertyValueFactory<PlageHoraire, CompteurElectrique>("Compteur"));

        DAOPlageHoraire daoPlageHoraire = new DAOPlageHoraire();
        tableView.getItems().setAll(daoPlageHoraire.getAll());
    }

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

    public void plageConsomationButton(ActionEvent actionEvent) {
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

    public void ajouterButton(ActionEvent actionEvent) {
        Parent root;
        Stage stage = new Stage();
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(new File("IHM/AjouterPlageDeConsomation.fxml").toURI().toURL());
            root = fxmlLoader.load();
            stage.setTitle("Ajouter une plage de consomation");
            stage.setScene(new Scene(root));
            stage.show();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        stage.setOnHiding(event -> {
            DAOPlageHoraire daoPlageHoraire = new DAOPlageHoraire();
            tableView.getItems().setAll(daoPlageHoraire.getAll());
        });
    }

    public void supprimerButton(ActionEvent actionEvent) {
        PlageHoraire plageHoraireASupprimer = tableView.getSelectionModel().getSelectedItem();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation suppression");
        alert.setHeaderText("Etes-vous sur de vouloir supprimer cette plage horaire ?");
        alert.setContentText("");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == ButtonType.OK){
            DAOPlageHoraire daoPlageHoraire = new DAOPlageHoraire();
            daoPlageHoraire.delete(plageHoraireASupprimer);
            tableView.getItems().removeAll(tableView.getSelectionModel().getSelectedItems());
        }
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
